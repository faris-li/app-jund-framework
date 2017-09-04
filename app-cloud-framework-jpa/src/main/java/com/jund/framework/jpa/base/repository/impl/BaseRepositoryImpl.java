package com.jund.framework.jpa.base.repository.impl;

import com.jund.framework.jpa.base.repository.BaseRepository;
import com.jund.framework.jpa.util.HqlUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by 段志军 on 2017/9/4.
 */
public class BaseRepositoryImpl<T, PK extends Serializable> extends SimpleJpaRepository<T, PK> implements BaseRepository<T, PK> {

    private final EntityManager entityManager;

    private final Class<T> domainClass;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findAllBySql(String sql, Object... values) {
        Query query = entityManager.createNativeQuery(sql, domainClass);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql, Object... values) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public <E> List<E> findAllByHql(String hql, Class<E> resultClass, Object... values) {
        Query query = entityManager.createQuery(hql, resultClass);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public List<T> findAllByHql(String hql, Object... values) {
        Query query = entityManager.createQuery(hql, domainClass);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public Page<T> findAllByHql(Pageable pageable, String hql, Object... values) {
        String countHql = "select count(*) " + HqlUtil.removeSelect(HqlUtil.removeOrders(hql));
        if (countHql.indexOf("fetch") != -1) {
            countHql = countHql.replace("fetch", "");
        }
        Query dataQuery = entityManager.createQuery(hql);
        Query countQuery = entityManager.createQuery(countHql);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                dataQuery.setParameter(i + 1, values[i]);
                countQuery.setParameter(i + 1, values[i]);
            }
        }
        Long totalSize = (Long) countQuery.getSingleResult();

        dataQuery.setFirstResult(pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        List<T> data = totalSize > pageable.getOffset() ? dataQuery.getResultList() : Collections.emptyList();
        return new PageImpl<T>(data, pageable, totalSize);
    }

    @Override
    public void excuteSql(String sql, Object... values) {
        Query query = entityManager.createNativeQuery(sql);
        if (null != values) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        query.executeUpdate();
    }

    @Override
    public void delete(PK[] ids) {
        for (PK id : ids) {
            T entity = findOne(id);
            if (null != entity) {
                String entityName = JpaEntityInformationSupport.getEntityInformation(getDomainClass(), entityManager).getEntityName();
                throw new EmptyResultDataAccessException(String.format("No %s Entity with id %s exists!", entityName, id), 1);
            }
            entityManager.remove(entity);
        }
    }
}
