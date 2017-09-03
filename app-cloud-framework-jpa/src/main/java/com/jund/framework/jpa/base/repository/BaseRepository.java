package com.jund.framework.jpa.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhijund on 2017/9/2.
 */
public abstract class BaseRepository<T, PK extends Serializable> implements JpaRepository<T, PK>{

    public abstract void remove(PK... ids);

    public abstract List<T> findAll(Map<String, Object> params);

    public abstract boolean exists(Map<String, Object> params);

}
