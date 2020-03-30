package com.natura.challenge.front.nurseapp.lazyDataModels.helper;


import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class RestPage<T> extends PageImpl<T> {

    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private boolean previousPage;
    private boolean first;
    private boolean next;
    private boolean last;
    private List<T> content;
    private RestSort sort;
    private RestPageable pageable;

    public RestPage() {
        super(new ArrayList<>());
    }

    public RestPage(List<T> content, RestPageable pageable, int totalElements) {
        super(content, pageable, totalElements);
    }


    public RestPageable getPageable() {
        return pageable;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }


    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(boolean previousPage) {
        this.previousPage = previousPage;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setPageable(RestPageable pageable) {
        this.pageable = pageable;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public RestSort getSort() {
        return sort;
    }

    public void setSort(RestSort sort) {
        this.sort = sort;
    }
}