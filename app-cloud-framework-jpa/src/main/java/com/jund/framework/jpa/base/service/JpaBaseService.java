package com.jund.framework.jpa.base.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhijund on 2017/9/3.
 */
public interface JpaBaseService<T, PK extends Serializable> {

    List<T> findAll();

    Page<T> findAll(Example<T> example, Pageable pageable);

    T findOne(PK id);

    void delete(PK... ids);

    void deleteOne(PK id);

    boolean exists(Example<T> example);

}
