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
        try {
            long id = getWorkUser(work.getUser().getId()).getWork_id();
            work.setWork_id(id);
            workRepository.save(work);
        }catch (Exception e){
            workRepository.save(work);
        }

    }

    @Override
    public Work getWorkUser(long user_id) {
        return workRepository.getWorkUser(user_id);
    }

    @Override
    public List<Work> getAll() {
        return workRepository.findAll();
    }
}
