package com.natura.challenge.front.nurseapp.lazyDataModels.helper;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;

public class RestSort extends Sort {
    boolean sorted;
    boolean unsorted;

    public RestSort() {
        super(new ArrayList<Order>());
    }

    public static RestSort unsorted() {
        return new RestSort();
    }

    @Override
    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    @Override
    public boolean isUnsorted() {
        return unsorted;
    }

    public void setUnsorted(boolean unsorted) {
        this.unsorted = unsorted;
    }
}
