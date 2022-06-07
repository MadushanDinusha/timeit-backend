package com.timeit.Skand1s.service;


import com.timeit.Skand1s.domain.Schedule;
import com.timeit.Skand1s.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }
}
