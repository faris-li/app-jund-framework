package com.jund.framework.jpa.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhijund on 2017/9/2.
 */
public interface BaseRepository<T, PK extends Serializable> extends JpaRepository<T, PK> {

    void remove(PK... ids);

    List<T> findAll(Map<String, Object> params);

    boolean exists(Map<String, Object> params);

}
