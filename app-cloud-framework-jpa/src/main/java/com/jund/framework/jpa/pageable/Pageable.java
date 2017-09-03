package com.jund.framework.jpa.pageable;

import org.springframework.data.domain.Sort;

public interface Pageable {

    int getPageIndex();

    int getPageSize();

    int getFirstIndex();

    int getMaxResults();

    boolean isFirstIndex();

    int getTotalCount();

    void setTotalCount(int totalCount);

    Sort getSort();

}
