package com.jund.framework.jpa.database.config;

import com.jund.framework.core.exception.JundRuntimeException;
import com.jund.framework.jpa.JpaConst;
import com.jund.framework.jpa.base.repository.factory.BaseRepositoryFactoryBean;
import com.jund.framework.jpa.database.DialectFactory;
import com.jund.framework.jpa.database.datasource.C3p0DataSource;
import com.jund.framework.jpa.database.datasource.DbcpDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by zhijund on 2017/9/2.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.jund"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class DbConfig {

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private JpaSettings jpaSettings;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        if (JpaConst.Database.C3P0.toLowerCase().equals(dbSettings.getPoolType())) {
            return c3p0DataSource();
        } else if (JpaConst.Database.DBCP.toLowerCase().equals(dbSettings.getPoolType())) {
            return dbcpDataSource();
        }
        throw new JundRuntimeException(JpaConst.ErrorCode.DATABASE_POOL_TYPE, "初始化数据库连接池失败！");
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.jund.*.entity", "com.jund.*.*.entity", "com.jund.*.*.model");
        Properties props = new Properties();
        props.setProperty("hibernate.ddl-auto", jpaSettings.getDdlAuto());
        props.setProperty("hibernate.naming-strategy", jpaSettings.getNamingStrategy());
        entityManagerFactoryBean.setJpaProperties(props);
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(jpaSettings.isShowSql());
        hibernateJpaVendorAdapter.setGenerateDdl(jpaSettings.isGenerateDdl());
        hibernateJpaVendorAdapter.setDatabase(DialectFactory.getDatabaseType(jpaSettings.getDatabase()));
        hibernateJpaVendorAdapter.setDatabasePlatform(jpaSettings.getDatabasePlatform());
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

    private String encryptPassword() {
        return (dbSettings.isEncrypted()) ? DESUtil.getDescriptString(dbSettings.getPassword()) : dbSettings.getPassword();
    }

    private DataSource c3p0DataSource() {
        C3p0DataSource c3p0DataSource = new C3p0DataSource();
        try {
            c3p0DataSource.setDriverClass(dbSettings.getDriverClassName());
        } catch (PropertyVetoException e) {
            throw new JundRuntimeException(JpaConst.ErrorCode.DATABASE_INITIAL, "初始化数据库驱动错误！");
        }
        c3p0DataSource.setJdbcUrl(dbSettings.getUrl());
        c3p0DataSource.setUser(dbSettings.getUsername());
        c3p0DataSource.setPassword(encryptPassword());
        c3p0DataSource.setAutoCommitOnClose(false);
        c3p0DataSource.setMaxConnectionAge(10);
        c3p0DataSource.setAcquireIncrement(dbSettings.getAcquireIncrement());
        c3p0DataSource.setMaxIdleTime(dbSettings.getMaxIdleTime());
        c3p0DataSource.setMaxPoolSize(dbSettings.getMaxPoolSize());
        c3p0DataSource.setMinPoolSize(dbSettings.getMinPoolSize());
        c3p0DataSource.setNumHelperThreads(dbSettings.getNumHelperThreads());
        return c3p0DataSource;
    }

    private DataSource dbcpDataSource() {
        DbcpDataSource dbcpDataSource = new DbcpDataSource();
        dbcpDataSource.setDriverClassName(dbSettings.getDriverClassName());
        dbcpDataSource.setUrl(dbSettings.getUrl());
        dbcpDataSource.setUsername(dbSettings.getUsername());
        dbcpDataSource.setPassword(encryptPassword());
        dbcpDataSource.setLogAbandoned(true);
        dbcpDataSource.setDefaultAutoCommit(true);
        dbcpDataSource.setInitialSize(dbSettings.getInitialSize());
        dbcpDataSource.setMaxActive(dbSettings.getMaxActive());
        dbcpDataSource.setMaxIdle(dbSettings.getMaxIdleTime());
        dbcpDataSource.setMaxWait(dbSettings.getMaxWaite());
        dbcpDataSource.setRemoveAbandoned(true);
        dbcpDataSource.setRemoveAbandonedTimeout(dbSettings.getRemoveAbandonedTimeout());
        dbcpDataSource.setValidationQuery(dbSettings.getValidationQuery());
        return dbcpDataSource;
    }
}
