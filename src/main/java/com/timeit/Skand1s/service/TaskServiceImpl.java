package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Schedule;
import com.timeit.Skand1s.domain.Task;
import com.timeit.Skand1s.repository.TaskRepository;
import com.timeit.Skand1s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveTask(Task task) {

        taskRepository.save(task);
    }

    public Timestamp getSysDate(){
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        return now;
    }


    @Override
    public List<Task> getUserTasks(String name) {
        long id = userRepository.getUserByUsername(name).getId();
        List<Task> tasks = taskRepository.getTasksByUserId(id,true);
        List<Task> activeTasks = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Task task :tasks){
            int toValue = sdf.format(task.getToDate()).compareTo(sdf.format(getSysDate()));
            int fromValue = sdf.format(task.getFromDate()).compareTo(sdf.format(getSysDate()));
            if (toValue>=0 && fromValue >=0){
                activeTasks.add(task);
            }else{
                taskRepository.updateTask(task.getId());
            }
        }
        return activeTasks;
    }

    @Override
    public List<Task> getAllTasksByUser(String name) {
        long id = userRepository.getUserByUsername(name).getId();
        return taskRepository.getAllTasksByUserId(id);
    }

    @Override
    public void updateTaskToDone(long id) {
        taskRepository.updateTask(id);
    }

    @Override
    public int getMonthlyNumberOfTasks() {
        List<Task> tasks = taskRepository.getMonthlyTasks();
        return tasks.size();
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.getById(id);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
