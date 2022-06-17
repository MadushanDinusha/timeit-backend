package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.AdminWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminWorkRepository extends JpaRepository<AdminWork,Long> {

    @Query("SELECT t FROM AdminWork t WHERE date= :date AND shift= :shift")
    AdminWork getAdminWorkForDate(String date, String shift);
}
