package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkRepository extends JpaRepository<Work,Long> {

    @Query("SELECT t FROM Work t WHERE user.user_id= :id")
    Work getWorkUser(long id);


    @Modifying
    @Query("UPDATE Work w SET w.shift =2 WHERE w.work_id =1")
    void updateWork(@Param("id") long id, @Param("shift") Work.Shift shift);
}
