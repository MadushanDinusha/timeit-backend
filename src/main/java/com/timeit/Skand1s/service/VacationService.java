package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Vacation;

import java.util.List;
import java.util.Optional;

public interface VacationService {
    public void saveVacation(Vacation vacation, String username);
    List<Vacation> getVacations(String user);
    List<Vacation> getAllPendingVacations();
    long getUsersForVacations(long id);
    Optional<Vacation> getById(long id);
    void upDateVacation(Vacation vacation);
    int getPending();
    List<Vacation>  getNumberOfDays(String name);
}
