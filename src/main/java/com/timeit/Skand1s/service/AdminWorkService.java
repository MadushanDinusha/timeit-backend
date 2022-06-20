package com.timeit.Skand1s.service;
import  com.timeit.Skand1s.domain.AdminWork;

import java.util.List;

public interface AdminWorkService {

    void saveAdminWork(AdminWork adminWork);
    List<Integer> getNumbersForDate();
    List<Integer>  getPhones();
}
