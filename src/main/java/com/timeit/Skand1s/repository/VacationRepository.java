package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.User;
import com.timeit.Skand1s.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface VacationRepository extends JpaRepository<Vacation,Long> {

    @Query("SELECT v FROM Vacation v WHERE v.user.user_id = :user_id")
    List<Vacation> findByUserName(long user_id);

    @Query("SELECT v FROM Vacation v WHERE v.status = 1")
    List<Vacation> getAllPendingVacations();

    @Query("Select user.user_id FROM Vacation WHERE id = :id")
    long  getUserForVacations(long id);
}
