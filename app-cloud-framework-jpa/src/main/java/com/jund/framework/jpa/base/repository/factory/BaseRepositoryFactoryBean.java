package com.jund.framework.jpa.base.repository.factory;

import com.jund.framework.jpa.base.repository.BaseRepository;
import com.jund.framework.jpa.base.repository.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by zhijund on 2017/9/2.
 */
public class BaseRepositoryFactoryBean<R extends BaseRepository<T, PK>, T, PK extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, PK> {

    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory<T, PK extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager entityManager;

        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, Serializable>((Class<T>) information.getDomainType(), entityManager);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }

}
