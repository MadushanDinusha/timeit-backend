package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Work;
import com.timeit.Skand1s.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    WorkRepository workRepository;

    @Override
    public void saveWork(Work work) {
        workRepository.save(work);
    }

    @Override
    public List<Work> getAll() {
        return workRepository.findAll();
    }
}
