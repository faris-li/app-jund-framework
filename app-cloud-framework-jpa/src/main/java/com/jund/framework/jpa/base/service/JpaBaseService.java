package com.jund.framework.jpa.base.service;

import com.jund.framework.jpa.pageable.Page;
import com.jund.framework.jpa.pageable.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhijund on 2017/9/3.
 */
public interface JpaBaseService<T, PK extends Serializable> {

    T query(PK id, String... fetchFields);

    List<T> queryAll();

    Page<T> queryForPage(Pageable pageable, Map<String, Object> params);

    List<T> queryForList(Map<String, Object> map);

    T save(T entity);

    void remove(PK... ids);

    boolean exists(Map<String, Object> map);

}
