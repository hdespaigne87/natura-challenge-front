package com.natura.challenge.front.nurseapp.lazyDataModels.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class RestPageable extends PageRequest {

    private long offset;
    private int pageSize;
    private int pageNumber;
    private boolean paged;
    private boolean unpaged;
    private RestSort sort;

    public RestPageable() {
        super(1, 1, Sort.unsorted());
    }

    public RestPageable(int page, int size, RestSort sort) {
        super(page, size, sort);
    }

    public static RestPageable of(int page, int size) {
        return of(page, size, RestSort.unsorted());
    }

    public static RestPageable of(int page, int size, RestSort sort) {
        return new RestPageable(page, size, sort);
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public boolean isUnpaged() {
        return unpaged;
    }

    public void setUnpaged(boolean unpaged) {
        this.unpaged = unpaged;
    }

    public RestSort getSort() {
        return sort;
    }

    public void setSort(RestSort sort) {
        this.sort = sort;
    }
}
