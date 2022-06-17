package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.AdminWork;
import com.timeit.Skand1s.repository.AdminWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminWorkServiceImpl implements AdminWorkService {

    @Autowired
    AdminWorkRepository adminWorkRepository;

    @Override
    public void saveAdminWork(AdminWork adminWork) {
      try{
//          adminWork.setAdmin_work_id(getAdminWorkForDate(adminWork.getDate(),adminWork.getShift()).getAdmin_work_id());
          adminWorkRepository.save(adminWork);
      }catch (Exception e){
          adminWorkRepository.save(adminWork);
      }
    }

    public AdminWork getAdminWorkForDate(String date,String shift){
        return adminWorkRepository.getAdminWorkForDate(date,shift);
    }

    @Override
    public int getNumbersForDate(String date, String shift) {

        return 0 ;
    }
}
