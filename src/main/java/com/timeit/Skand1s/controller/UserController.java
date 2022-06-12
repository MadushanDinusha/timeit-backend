package com.timeit.Skand1s.controller;

import com.timeit.Skand1s.config.AuthenticationBean;
import com.timeit.Skand1s.domain.*;
import com.timeit.Skand1s.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    CaseService caseService;
    @Autowired
    VacationService vacationService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    MailService mailService;



    @GetMapping(path = "/basicauth")
    public AuthenticationBean user(Principal user) {
        return new AuthenticationBean("You are authenticated");
    }

    @GetMapping("/currentusername")
    public String currentUserName(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/task/getNumberOfTasks/{name}")
    public ResponseEntity<List<Integer>> getNumberOfTasks(@PathVariable String name) {
        try {
            List<Integer> integers = new ArrayList<>();

            List<Task> tasks = taskService.getAllTasksByUser(name);
            int phone = 0;
            int meeting = 0;
            int BBØ =0;
            int ho = 0;
            int mails = 0;
            int fri =0;
            int syg =0;
            int andet =0;

            for(Task task : tasks){
                if("Fri".equalsIgnoreCase(task.getType().name())){
                    fri++;
                }else if("Mails".equalsIgnoreCase(task.getType().name())){
                    mails++;
                }else if("HO".equalsIgnoreCase(task.getType().name())){
                    ho++;
                }else if("Meeting".equals(task.getType().name())){
                    meeting++;
                }else if("BBØ".equals(task.getType().name())){
                    BBØ++;
                }else if("Andet".equals(task.getType().name())){
                    andet++;
                }else if("Syg".equals(task.getType().name())){
                    syg++;
                }else if("Phone".equals(task.getType().name())){
                    phone++;
                }
            }
            integers.add(phone);
            integers.add(meeting);
            integers.add(BBØ);
            integers.add(ho);
            integers.add(mails);
            integers.add(fri);
            integers.add(syg);
            integers.add(andet);
            return new ResponseEntity<>(integers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getUsersByUserName/{fullName}")
    public ResponseEntity<String> getUserName(@PathVariable String fullName) {
        try {
            String userName = userService.getUserNameByFullName(fullName);
            return new ResponseEntity<>(userName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/save/{role}")
    public ResponseEntity<?> saveUser(@RequestBody User user, @PathVariable("role") String roles) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            Set<Role> role = new HashSet<Role>();
            Role roleObj = new Role();
            roleObj.setName(roles);
            role.add(roleObj);
            user.setRoles(role);
            user.setEnabled(true);
            userService.saveUser(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRoles/{name}")
    public ResponseEntity<User> getRoles(@PathVariable("name") String name) {
        try {
            User user = userService.getUserRole(name);
            String userRole = user.getRoles().iterator().next().getName();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUerId/{name}")
    public ResponseEntity<Long> getId(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(userService.getUserId(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTasks/{name}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable("name") String name) {
        try {

            List<Task> taskList = taskService.getUserTasks(name);
            List<Task> sortedByFromDate = new ArrayList<>();
            Collections.sort(taskList, new SortByDate());
            for (Task t : taskList) {
                sortedByFromDate.add(t);
            }

            return new ResponseEntity<>(sortedByFromDate, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/task/save/{name}")
    public ResponseEntity<?> saveTask(@RequestBody Task task, @PathVariable("name") String name) {
        try {
           SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           gmtDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
           String fromDate = gmtDateFormat.format(task.getFromDate());
           String toDate = gmtDateFormat.format(task.getToDate());
           Timestamp convertedFromDate = Timestamp.valueOf(fromDate);
           Timestamp convertedToDate = Timestamp.valueOf(toDate);
           task.setFromDate(convertedFromDate);
           task.setToDate(convertedToDate);
             System.out.println(task.getFromDate());
            User user = new User();
            user.setId(userService.getUserId(name));
            task.setUser(user);
            task.setActive(true);
            taskService.saveTask(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTasks/{name}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable("name") String name) {
        try {
            List<Task> tasks = taskService.getAllTasksByUser(name);
            System.out.println(tasks);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/saveCase/{name}")
    public ResponseEntity<?> saveCase(@RequestBody Case cases, @PathVariable("name") String name) {
        try {
            User user = new User();
            user.setId(userService.getUserId(name));
            cases.setUser(user);
            caseService.saveCase(cases);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllCases/{name}")
    public ResponseEntity<List<Case>> getAllCases(@PathVariable("name") String name) {
        try {

            List<Case> caseList = caseService.getAllByUser(name);
            List<Case> sortedByFromDate = new ArrayList<>();
            Collections.sort(caseList, new SortCaseByDate());
            for (Case t : caseList) {
                sortedByFromDate.add(t);
            }
            return new ResponseEntity<>(caseList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/updateTask/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable("task_id") long task_id) {
        try {
            taskService.updateTaskToDone(task_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("updateTask/{name}")
    public ResponseEntity<?> updateTasks(@PathVariable("name") String name, @RequestBody Task task){
        try{
            Task taskUp = taskService.getTaskById(task.getId());
            taskUp.setUser(task.getUser());
            taskUp.setFromDate(task.getFromDate());
            taskUp.setToDate(task.getToDate());
           Optional<User> user =  userService.getUserById(userService.getUserId(name));
           taskUp.setUser(user.get());
            taskService.updateTask(taskUp);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save/vacation/{name}")
    public ResponseEntity<?> saveVacation(@PathVariable("name") String name, @RequestBody Vacation vacation) {
        try {
            vacation.setStatus(Vacation.Status.pending);
            vacationService.saveVacation(vacation, name);
            long id = userService.getUserId(name);
            String userMail = userService.getUserById(id).get().getEmail();
            mailService.sendmail(name,"kasper.franklin.nielsen@tryg.dk",userMail, vacation.getFromDate(),vacation.getToDate(),
                    vacation.getComment());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVacationByUser/{user}")
    public ResponseEntity<List<Vacation>> getVacation(@PathVariable("user") String user) {
        try {
            List<Vacation> list = vacationService.getVacations(user);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllPendingVacation")
    public ResponseEntity<List<Vacation>> getAllPendingVacations() {
        try {
            return new ResponseEntity<>(vacationService.getAllPendingVacations(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUserForVacation/{id}")
    public ResponseEntity<Optional<User>> getUserForVacation(@PathVariable("id") long id) {
        try {
            Optional<User> user = userService.getUserById(vacationService.getUsersForVacations(id));

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getVacationById/{id}")
    public ResponseEntity<Optional<Vacation>> getVacationById(@PathVariable("id") long id) {
        try {
            Optional<Vacation> vacation = vacationService.getById(id);
            return new ResponseEntity<>(vacation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("updateStatus/{status}/{id}/{userId}")
    public ResponseEntity<?> updateStatus(@PathVariable("status") String status, @PathVariable("id") long id,
                                          @PathVariable("userId") long userId){
        try {
            Optional<Vacation> vacation = vacationService.getById(id);
            if(status.equals("approved")){
                vacation.get().setStatus(Vacation.Status.Completed);

            }else if(status.equals("rejected")){
                vacation.get().setStatus(Vacation.Status.Rejected);
            }
            String userMail = userService.getUserById(userId).get().getEmail();
//            mailService.sendmailApprove(userMail,"kasper.franklin.nielsen@tryg.dk",vacation.get().getStatus().name());
            vacationService.upDateVacation(vacation.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addSchedule/{name}")
    public ResponseEntity<?> addSchedule(@RequestBody Schedule schedule,@PathVariable("name") String name){
        try {
            SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String fromDate = gmtDateFormat.format(schedule.getFromDate());
            String toDate = gmtDateFormat.format(schedule.getToDate());
            Timestamp convertedFromDate = Timestamp.valueOf(fromDate);
            Timestamp convertedToDate = Timestamp.valueOf(toDate);
            schedule.setFromDate(convertedFromDate);
            schedule.setToDate(convertedToDate);
            User user = new User();
            user.setId(userService.getUserId(name));
            schedule.setUser(user);
            scheduleService.save(schedule);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllTasks")
    public ResponseEntity<List<Task>> getAll(){
        try {
            return new ResponseEntity<>( taskService.getAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getNumberOfTasks")
    public ResponseEntity<Number> getMonthlyNumberOfTasks(){
        try {
            return new ResponseEntity<>(taskService.getMonthlyNumberOfTasks(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getUserCount")
    public ResponseEntity<Number> getUserCount(){
        try {
            return new ResponseEntity<>(userService.getUserCount(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getPending")
    public ResponseEntity<Number> getPending(){
        try {
            return new ResponseEntity<>(vacationService.getPending(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getVacationDays/{name}")
    public ResponseEntity<List<Vacation>> getVacations(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(vacationService.getNumberOfDays(name),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("userOnVac")
    public ResponseEntity<Integer> getUsersOnVac(){
        try {
            return new ResponseEntity<>(vacationService.changeVacationToDone(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}