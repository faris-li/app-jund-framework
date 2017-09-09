package com.jund.framework.jpa.pageable;

import java.util.List;
import org.springframework.data.domain.Sort;

public interface Page<T> extends Iterable<T> {

    int getPageIndex();

    int getPageSize();

    int getTotalCount();

    int getPages();

    List<T> getData();

    Sort getSort();

}
