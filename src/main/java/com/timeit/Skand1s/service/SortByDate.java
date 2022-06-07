package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Task;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class SortByDate implements Comparator<Task> {


    @Override
    public int compare(Task o1, Task o2) {
        return o1.getFromDate().toString().compareTo(o2.getFromDate().toString());
    }

    @Override
    public Comparator<Task> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<Task> thenComparing(Comparator<? super Task> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<Task> thenComparing(Function<? super Task, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Task> thenComparing(Function<? super Task, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<Task> thenComparingInt(ToIntFunction<? super Task> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<Task> thenComparingLong(ToLongFunction<? super Task> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Task> thenComparingDouble(ToDoubleFunction<? super Task> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
