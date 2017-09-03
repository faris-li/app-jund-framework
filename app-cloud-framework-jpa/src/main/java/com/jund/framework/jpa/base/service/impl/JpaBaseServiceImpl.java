package com.jund.framework.jpa.base.service.impl;

import com.jund.framework.jpa.base.repository.BaseRepository;
import com.jund.framework.jpa.base.service.JpaBaseService;
import com.jund.framework.jpa.pageable.Page;
import com.jund.framework.jpa.pageable.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhijund on 2017/9/3.
 */
public abstract class JpaBaseServiceImpl<T, PK extends Serializable> implements JpaBaseService<T, PK> {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected abstract BaseRepository<T, PK> getRepository();

    @Override
    public List<T> queryAll() {
        return getRepository().findAll();
    }

    @Override
    public Page<T> queryForPage(Pageable pageable, Map<String, Object> map) {
        //return getRepository().findAll(pageable, map);
        return null;
    }

    @Override
    public List<T> queryForList(Map<String, Object> map) {
        return getRepository().findAll(map);
    }

    @Override
    public T query(PK id, String... fetchFields) {
        return getRepository().findOne(id);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    @Transactional
    public void remove(PK... ids) {
        getRepository().remove(ids);
    }

    @Override
    public boolean exists(Map<String, Object> map) {
        return getRepository().exists(map);
    }

}
