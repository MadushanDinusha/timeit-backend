package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Task;
import com.timeit.Skand1s.domain.Vacation;
import com.timeit.Skand1s.domain.Work;
import com.timeit.Skand1s.repository.TaskRepository;
import com.timeit.Skand1s.repository.VacationRepository;
import com.timeit.Skand1s.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    WorkRepository workRepository;
    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskService taskService;

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
                if ((fromValue <=0 && toValue>=0  )) {

                }else{
                    if(checkMeeting(id,work.getUser().getUsername())){
                        noHoliday.add(work);
                    }
                }
            }catch (NoSuchElementException e){
                if(checkMeeting(id,work.getUser().getUsername())){
                    noHoliday.add(work);
                }

            }

        }
        return noHoliday;
    }

    boolean checkMeeting(long id,String name){
        List<Task> taskList = taskService.getUserTasks(name);
        List<Task> sortedByFromDate = new ArrayList<>();

        Work work = workRepository.getWorkUser(id);
        work.getShift().name();
        String time1;
        String time2;
        String time1_1;
        String time2_2;
        if(work.getShift().equals(Work.Shift.Morning)){

            Calendar cal1 = Calendar.getInstance();
            LocalDateTime now = LocalDateTime.now();
            cal1.set(Calendar.YEAR,now.getYear());
            cal1.set(Calendar.MONTH,now.getMonthValue() -1);
            cal1.set(Calendar.HOUR_OF_DAY,8);
            cal1.set(Calendar.MINUTE,0);
            cal1.set(Calendar.SECOND,0);
            cal1.set(Calendar.MILLISECOND,0);
            Date d1 = cal1.getTime();
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.HOUR_OF_DAY,9);
            cal2.set(Calendar.MINUTE,59);
            cal2.set(Calendar.SECOND,0);
            cal2.set(Calendar.MILLISECOND,0);
            Date d2 = cal2.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMMM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));

            time1 = sdf.format(d1);
            time2 = sdf.format(d2);

            for (Task t : taskList) {
                if(sdf1.format(d1).compareTo(sdf1.format(t.getFromDate()))==0){
                    time1_1=sdf.format(t.getFromDate());
                    time2_2 = sdf.format(t.getToDate());
                    if(time1.compareTo(time1_1)<=0 &&time2.compareTo(time2_2)>=0){
                        return false;
                    }
                }

            }

        }
        else if(work.getShift().equals(Work.Shift.Afternoon)){

            Calendar cal1 = Calendar.getInstance();
            LocalDateTime now = LocalDateTime.now();
            cal1.set(Calendar.YEAR,now.getYear());
            cal1.set(Calendar.MONTH,now.getMonthValue() -1);
            cal1.set(Calendar.HOUR_OF_DAY,10);
            cal1.set(Calendar.MINUTE,0);
            cal1.set(Calendar.SECOND,0);
            cal1.set(Calendar.MILLISECOND,0);
            Date d1 = cal1.getTime();
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.HOUR_OF_DAY,12);
            cal2.set(Calendar.MINUTE,59);
            cal2.set(Calendar.SECOND,0);
            cal2.set(Calendar.MILLISECOND,0);
            Date d2 = cal2.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMMM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));

            time1 = sdf.format(d1);
            time2 = sdf.format(d2);

            for (Task t : taskList) {
                if(sdf1.format(d1).compareTo(sdf1.format(t.getFromDate()))==0){
                    time1_1=sdf.format(t.getFromDate());
                    time2_2 = sdf.format(t.getToDate());
                    if(time1.compareTo(time1_1)<=0 &&time2.compareTo(time2_2)>=0){
                        return false;
                    }
                }

            }

        }
        else if(work.getShift().equals(Work.Shift.Evening)){

            Calendar cal1 = Calendar.getInstance();
            LocalDateTime now = LocalDateTime.now();
            cal1.set(Calendar.YEAR,now.getYear());
            cal1.set(Calendar.MONTH,now.getMonthValue() -1);
            cal1.set(Calendar.HOUR_OF_DAY,13);
            cal1.set(Calendar.MINUTE,0);
            cal1.set(Calendar.SECOND,0);
            cal1.set(Calendar.MILLISECOND,0);
            Date d1 = cal1.getTime();
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.HOUR_OF_DAY,15);
            cal2.set(Calendar.MINUTE,59);
            cal2.set(Calendar.SECOND,0);
            cal2.set(Calendar.MILLISECOND,0);
            Date d2 = cal2.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMMM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));

            time1 = sdf.format(d1);
            time2 = sdf.format(d2);

            for (Task t : taskList) {
                if(sdf1.format(d1).compareTo(sdf1.format(t.getFromDate()))==0){
                    time1_1=sdf.format(t.getFromDate());
                    time2_2 = sdf.format(t.getToDate());
                    if(time1.compareTo(time1_1)<=0 &&time2.compareTo(time2_2)>=0){
                        return false;
                    }
                }

            }

        }

//        List<Task> tasks = taskRepository.getTasksByUserId(id,true);
//        for (Task task : tasks){
//           if(task.getType().equals(Task.Type.Meeting)||task.getType().equals(Task.Type.Phone)||task.getType().equals(Task.Type.HO)||
//                   task.getType().equals(Task.Type.Mails)||task.getType().equals(Task.Type.BBØ)||task.getType().equals(Task.Type.Fri)
//           ||task.getType().equals(Task.Type.Syg)||task.getType().equals(Task.Type.Andet)){
//              return false;
//           }
//
//        }
        return true;
    }
}
