package com.timeit.Skand1s.service;

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
            Vacation vacation = vacationRepository.checkHoliday(id);
            int toValue = sdf.format(vacation.getToDate()).compareTo(sdf.format(getSysDate()));
            int fromValue = sdf.format(vacation.getFromDate()).compareTo(sdf.format(getSysDate()));
            if (toValue>=0 && fromValue >=0){

            }else{
                noHoliday.add(work);
            }
        }
        System.out.println(noHoliday);

        return noHoliday;
    }

//    boolean checkMeeting(long id){
//        if(taskRepository.getTasksByUserId(id,true) != null){
//            return false;
//        }else {
//            return true;
//        }
//    }
}
