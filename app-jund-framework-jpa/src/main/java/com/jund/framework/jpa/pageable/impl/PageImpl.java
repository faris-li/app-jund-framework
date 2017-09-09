/**
 *
 */
package com.jund.framework.jpa.pageable.impl;

import com.jund.framework.jpa.pageable.Page;
import com.jund.framework.jpa.pageable.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by zhijund on 2017/9/3.
 */
public class PageImpl<T> implements Page<T> {

    private Pageable pageable;

    private final List<T> data = new ArrayList<T>();

    public PageImpl(List<T> content, Pageable pageable) {
        this.data.addAll(content);
        this.pageable = pageable;
    }

    public int getPageIndex() {
        return pageable == null ? 1 : pageable.getPageIndex();
    }

    public int getPageSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }

    public int getTotalCount() {
        return pageable == null ? 0 : pageable.getTotalCount();
    }

    public int getPages() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) getTotalCount() / (double) getPageSize());
    }

    public List<T> getData() {
        return this.data;
    }

    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public Sort getSort() {
        return pageable == null ? null : pageable.getSort();
    }

}
