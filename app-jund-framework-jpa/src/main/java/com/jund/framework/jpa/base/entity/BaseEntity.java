package com.jund.framework.jpa.base.entity;

import com.jund.framework.core.entity.AbstractEntity;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by zhijund on 2017/9/2.
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity implements Serializable {

    @Description("{title: '主键id'}")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

}
