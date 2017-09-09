package com.jund.framework.jpa.pageable.impl;

import com.jund.framework.jpa.pageable.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhijund on 2017/9/3.
 */
public class PageableImpl implements Pageable {

    /**
     * 索引页
     */
    private final int pageIndex;

    /**
     * 页大小
     */
    private final int pageSize;

    /**
     * 排序对象
     */
    private final Sort sort;

    /**
     * 总记录数
     */
    private int totalCount;


    public PageableImpl() {
        this(0, 0);
    }

    public PageableImpl(int page, int size) {
        this(page, size, null);
    }

    public PageableImpl(int pageIndex, int pageSize, Sort sort) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public PageableImpl(int pageIndex, int pageSize, Direction direction, String... properties) {
        this(pageIndex, pageSize, new Sort(direction, properties));
    }

    public PageableImpl(int pageIndex, int pageSize, Direction[] directions, String... properties) {
        Assert.notNull(directions);
        Assert.notNull(properties);

        List<Order> orders = new ArrayList<Order>();

        for (int i = 0; i < directions.length; i++) {
            orders.add(new Order(directions[i], properties[i]));
        }

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sort = new Sort(orders);
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getFirstIndex() {
        return (pageIndex - 1) * pageIndex + 1;
    }

    public int getMaxResults() {
        return pageIndex * pageIndex;
    }

    public boolean isFirstIndex() {
        return pageIndex <= 1;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

}
