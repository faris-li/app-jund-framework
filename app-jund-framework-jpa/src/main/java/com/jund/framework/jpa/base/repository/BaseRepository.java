package com.jund.framework.jpa.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhijund on 2017/9/2.
 */
@NoRepositoryBean
public interface BaseRepository<T, PK extends Serializable> extends JpaRepository<T, PK> {

    List<T> findAllByHql(String sql, Object... values);

    Page<T> findAllByHql(Pageable pageable, String sql, Object... values);

    <E> List<E> findAllByHql(String sql, Class<E> resultClass, Object... values);

    List<Map<String, Object>> findMapBySql(String sql, Object... values);

    List<T> findAllBySql(String sql, Object... values);

    void excuteSql(String sql, Object... values);

    void delete(PK... ids);

}
