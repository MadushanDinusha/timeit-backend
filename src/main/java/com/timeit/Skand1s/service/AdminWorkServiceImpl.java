package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.AdminWork;
import com.timeit.Skand1s.domain.Work;
import com.timeit.Skand1s.repository.AdminWorkRepository;
import com.timeit.Skand1s.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminWorkServiceImpl implements AdminWorkService {

    @Autowired
    AdminWorkRepository adminWorkRepository;
    @Autowired
    WorkRepository workRepository;

    @Override
    public void saveAdminWork(AdminWork adminWork) {
      try{
          System.out.println(adminWork);
          adminWorkRepository.save(adminWork);
      }catch (Exception e){

      }
    }

    @Override
    public List<Integer> getNumbersForDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        String today = str.toLowerCase(Locale.ROOT).substring(0,3);
        List<Integer> returnList = new ArrayList<>();

       List<AdminWork> adminWorkList =  adminWorkRepository.findAll();
        if(today.equals("mon")){
            returnList.add(adminWorkList.get(0).getMon89());
            returnList.add(adminWorkList.get(0).getMon910());
            returnList.add(adminWorkList.get(0).getMon1011());
            returnList.add(adminWorkList.get(0).getMon1112());
            returnList.add(adminWorkList.get(0).getMon1213());
            returnList.add(adminWorkList.get(0).getMon1314());
            returnList.add(adminWorkList.get(0).getMon1415());
            returnList.add(adminWorkList.get(0).getMon1516());
            returnList.add(adminWorkList.get(0).getMon1617());

        }
        else if(today.equals("tue")){
            returnList.add(adminWorkList.get(0).getTue89());
            returnList.add(adminWorkList.get(0).getTue910());
            returnList.add(adminWorkList.get(0).getTue1011());
            returnList.add(adminWorkList.get(0).getTue1112());
            returnList.add(adminWorkList.get(0).getTue1213());
            returnList.add(adminWorkList.get(0).getTue1314());
            returnList.add(adminWorkList.get(0).getTue1415());
            returnList.add(adminWorkList.get(0).getTue1516());
            returnList.add(adminWorkList.get(0).getTue1617());

        }
        else if(today.equals("wed")){
            returnList.add(adminWorkList.get(0).getWed89());
            returnList.add(adminWorkList.get(0).getWed910());
            returnList.add(adminWorkList.get(0).getWed1011());
            returnList.add(adminWorkList.get(0).getWed1112());
            returnList.add(adminWorkList.get(0).getWed1213());
            returnList.add(adminWorkList.get(0).getWed1314());
            returnList.add(adminWorkList.get(0).getWed1415());
            returnList.add(adminWorkList.get(0).getWed1516());
            returnList.add(adminWorkList.get(0).getWed1617());

        }

        else if(today.equals("thu")){
            returnList.add(adminWorkList.get(0).getThu89());
            returnList.add(adminWorkList.get(0).getThu910());
            returnList.add(adminWorkList.get(0).getThu1011());
            returnList.add(adminWorkList.get(0).getThu1112());
            returnList.add(adminWorkList.get(0).getThu1213());
            returnList.add(adminWorkList.get(0).getThu1314());
            returnList.add(adminWorkList.get(0).getThu1415());
            returnList.add(adminWorkList.get(0).getThu1516());
            returnList.add(adminWorkList.get(0).getThu1617());

        }else {
            returnList.add(adminWorkList.get(0).getFri89());
            returnList.add(adminWorkList.get(0).getFri910());
            returnList.add(adminWorkList.get(0).getFri1011());
            returnList.add(adminWorkList.get(0).getFri1112());
            returnList.add(adminWorkList.get(0).getFri1213());
            returnList.add(adminWorkList.get(0).getFri1314());
            returnList.add(adminWorkList.get(0).getFri1415());
            returnList.add(adminWorkList.get(0).getFri1516());
            returnList.add(adminWorkList.get(0).getFri1617());
        }
        Map<String,Integer> map = new HashMap<>();
        int morning = returnList.get(0)+returnList.get(1);
        int afternoon = returnList.get(2)+returnList.get(3)+returnList.get(4);
        int evening = returnList.get(5)+returnList.get(6)+returnList.get(7);
        map.put("Morning",morning);
        map.put("Afternoon",afternoon);
        map.put("Evening",evening);

        List<Work> works = workRepository.findAll();
    

        int haveMorning = 0;
        int haveAfternoon = 0;
        int haveEvening = 0;

        
        for(Work work : works){
            if(work.getShift().name().equals("Morning")){

            }else if(work.getShift().name().equals("Afternoon")){
                haveAfternoon++;
            }else {
                haveEvening++;
            }
        }

        return returnList;
    }

    public List<Integer> getPhones(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        String today = str.toLowerCase(Locale.ROOT).substring(0,3);
        List<AdminWork> adminWorkList = adminWorkRepository.findAll();
        AdminWork adminWork = adminWorkList.get(0);
        List<Integer> adminList = new ArrayList<>();
        int morning = 0;
        int afternoon = 0;
        int evening = 0;

        if (today.equals("mon")){
            System.out.println("monday");
           morning =  adminWork.getMon89() + adminWork.getMon910();
           afternoon = adminWork.getMon1011()+adminWork.getMon1112()+adminWork.getMon1213();
           evening = adminWork.getMon1314()+adminWork.getMon1415()+adminWork.getMon1516();
           adminList.add(morning);
           adminList.add(afternoon);
           adminList.add(evening);
        }
        return adminList;
    }

    @Override
    public List<AdminWork> getAll() {
        return adminWorkRepository.findAll();
    }
}
