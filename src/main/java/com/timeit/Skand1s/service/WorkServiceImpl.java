package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Vacation;
import com.timeit.Skand1s.domain.Work;
import com.timeit.Skand1s.repository.VacationRepository;
import com.timeit.Skand1s.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    WorkRepository workRepository;
    @Autowired
    VacationRepository vacationRepository;

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
        List<Work> workList = workRepository.findAll();
        for(Work work : workList){
            long id = work.getUser().getId();
            checkHoliday(id);
        }
        return null;
    }

    public boolean checkHoliday(long id){
     Vacation vacation = vacationRepository.checkHoliday(id);
        System.out.println(vacation);
     return true;
    }
}
