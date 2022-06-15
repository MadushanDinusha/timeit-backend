package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Work;

import java.util.List;

public interface WorkService {

    void saveWork(Work work);
    List<Work> getAll();
}
