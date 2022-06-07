package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Case;

import java.util.List;

public interface CaseService {

    void saveCase(Case cases);
    List<Case> getAllByUser(String name);
}
