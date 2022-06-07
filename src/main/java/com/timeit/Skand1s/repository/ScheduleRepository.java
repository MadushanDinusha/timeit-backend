package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    @Query( "FROM Schedule")
    List<Schedule> getAllSchedule();
}
