package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.User;
import com.timeit.Skand1s.domain.Vacation;
import com.timeit.Skand1s.repository.UserRepository;
import com.timeit.Skand1s.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacationServiceImpl implements VacationService {

    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveVacation(Vacation vacation, String username){

        User user = userRepository.getUserByUsername(username);
        vacation.setUser(user);
        vacationRepository.save(vacation);
    }

    @Override
    public List<Vacation> getVacations(String username) {
        User user = userRepository.getUserByUsername(username);
        long userId = user.getId();
        List<Vacation> list = vacationRepository.findByUserName(userId);
        return list;
    }

    @Override
    public List<Vacation> getAllPendingVacations() {

        return vacationRepository.getAllPendingVacations();
    }

    @Override
    public long getUsersForVacations(long id){
        return vacationRepository.getUserForVacations(id);
    }

    @Override
    public Optional<Vacation> getById(long id) {
        return vacationRepository.findById(id);
    }

    @Override
    public void upDateVacation(Vacation vacation) {
        vacationRepository.save(vacation);
    }

    @Override
    public int getPending() {
        List<Vacation> vacations = vacationRepository.getAllPending();
        return vacations.size();
    }

    @Override
    public List<Vacation> getNumberOfDays(String name) {
        User user = userRepository.getUserByUsername(name);
        long userId = user.getId();
        List<Vacation> list = vacationRepository.findByUserName(userId);
        return list;
    }

    public Timestamp getSysDate(){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        return now;
    }

    @Override
    public int changeVacationToDone() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        List<Vacation> vacations = vacationRepository.getAllApprovedVacations();
        List<Vacation> onGoingVac = new ArrayList<>();
        for (Vacation vacation : vacations){
            int toValue = sdf.format(vacation.getToDate()).compareTo(sdf.format(getSysDate()));
            int fromValue = sdf.format(vacation.getFromDate()).compareTo(sdf.format(getSysDate()));
            if( fromValue <=0 && toValue>=0){
                onGoingVac.add(vacation);
                System.out.println(vacation);
            }else if(toValue <=0){
                vacation.setStatus(Vacation.Status.Done);
                upDateVacation(vacation);
            }
        }
        return onGoingVac.size();
    }
}
