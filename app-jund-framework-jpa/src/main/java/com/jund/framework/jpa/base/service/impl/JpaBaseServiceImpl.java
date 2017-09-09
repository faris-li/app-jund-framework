package com.jund.framework.jpa.base.service.impl;

import com.jund.framework.jpa.base.repository.BaseRepository;
import com.jund.framework.jpa.base.service.JpaBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhijund on 2017/9/3.
 */
public abstract class JpaBaseServiceImpl<T, PK extends Serializable> implements JpaBaseService<T, PK> {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    public T findOne(PK id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void deleteOne(PK id) {
        getRepository().delete(id);
    }

    @Transactional
    public void delete(PK... ids) {
        getRepository().delete(ids);
    }

    @Override
    public boolean exists(Example<T> example) {
        return getRepository().exists(example);
    }

    protected abstract BaseRepository<T, PK> getRepository();

}
