package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.AdminWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminWorkRepository extends JpaRepository<AdminWork,Long> {

}
