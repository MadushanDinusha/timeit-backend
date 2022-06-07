package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void saveTask(Task task);
    List<Task> getUserTasks(String name);
    List<Task> getAllTasksByUser(String name);
    void updateTaskToDone(long id);
}
