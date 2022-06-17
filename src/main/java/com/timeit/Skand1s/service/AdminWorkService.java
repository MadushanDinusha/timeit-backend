package com.timeit.Skand1s.service;
import  com.timeit.Skand1s.domain.AdminWork;
public interface AdminWorkService {

    void saveAdminWork(AdminWork adminWork);
    int getNumbersForDate(String date, String shift);
}
