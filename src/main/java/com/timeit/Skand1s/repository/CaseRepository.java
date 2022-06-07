package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.Case;
import com.timeit.Skand1s.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CaseRepository extends JpaRepository<Case, Long> {

    @Query("FROM Case t WHERE t.user.user_id = :user_id")
    List<Case> getAllTasksByUserId(long user_id);



}
