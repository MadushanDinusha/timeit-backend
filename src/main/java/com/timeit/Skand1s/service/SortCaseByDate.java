package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Case;

import java.util.Comparator;

public class SortCaseByDate implements Comparator<Case> {

    @Override
    public int compare(Case o1, Case o2) {
        return o1.getOldestDate().toString().compareTo(o2.getOldestDate().toString());
    }
}
