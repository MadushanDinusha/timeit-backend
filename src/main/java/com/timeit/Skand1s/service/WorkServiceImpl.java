package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Task;
import com.timeit.Skand1s.domain.Vacation;
import com.timeit.Skand1s.domain.Work;
import com.timeit.Skand1s.repository.TaskRepository;
import com.timeit.Skand1s.repository.VacationRepository;
import com.timeit.Skand1s.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    WorkRepository workRepository;
    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    TaskRepository taskRepository;

    @Override
    public void saveWork(Work work) {
        try {
            long id = getWorkUser(work.getUser().getId()).getWork_id();
            work.setWork_id(id);
            workRepository.save(work);
        }catch (Exception e){
            workRepository.save(work);
        }

    }

    @Override
    public Work getWorkUser(long user_id) {
        return workRepository.getWorkUser(user_id);
    }

    public Timestamp getSysDate(){
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        return now;
    }

    @Override
    public List<Work> getAll() {
        List<Work> workList = workRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Work> noHoliday = new ArrayList<>();

        for(Work work : workList){
            long id = work.getUser().getId();
            try{
                Optional<Vacation> vacation = Optional.ofNullable(vacationRepository.checkHoliday(id));
                int toValue = sdf.format(vacation.get().getToDate()).compareTo(sdf.format(getSysDate()));
                int fromValue = sdf.format(vacation.get().getFromDate()).compareTo(sdf.format(getSysDate()));
                if ((toValue>=0 && fromValue >=0 )) {

                }else{
                    if(checkMeeting(id)){
                        noHoliday.add(work);
                    }
                }
            }catch (NoSuchElementException e){
                if(checkMeeting(id)){
                    noHoliday.add(work);
                }

            }



        }

        System.out.println(noHoliday);

        return noHoliday;
    }

    boolean checkMeeting(long id){
        List<Task> tasks = taskRepository.getTasksByUserId(id,true);
        for (Task task : tasks){
            System.out.println(task);
           if(task.getType().equals(Task.Type.Meeting)){
              return false;
           }

        }
        return true;
    }
}
