package com.timeit.Skand1s.repository;


import com.timeit.Skand1s.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.fullName = :fullName")
    User getUserByFullName(@Param("fullName") String fullName);
}