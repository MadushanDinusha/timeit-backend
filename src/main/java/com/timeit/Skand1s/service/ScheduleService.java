package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    void save(Schedule schedule);
    List<Schedule> getAll();
}
