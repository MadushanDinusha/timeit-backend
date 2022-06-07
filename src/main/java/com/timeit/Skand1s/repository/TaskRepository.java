package com.timeit.Skand1s.repository;

import com.timeit.Skand1s.domain.Task;
import com.timeit.Skand1s.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task,Long> {

    //nList<Task> findByUserId(long id);

    @Query("SELECT t FROM Task t WHERE t.user.user_id = :user_id AND isActive= :isActive")
    List<Task> getTasksByUserId(@Param("user_id") long user_id,@Param("isActive") boolean isActive);

    @Modifying
    @Query("update Task t set t.isActive = false where t.id = :id")
    void updateTask(@Param("id") long id);

    @Query("SELECT t FROM Task t WHERE t.user.user_id = :user_id")
    List<Task> getAllTasksByUserId(long user_id);

    @Query("SELECT t FROM Task t WHERE t.isActive=1")
    List<Task> getMonthlyTasks();

}
