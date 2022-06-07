package com.timeit.Skand1s.service;

import com.timeit.Skand1s.domain.Case;
import com.timeit.Skand1s.repository.CaseRepository;
import com.timeit.Skand1s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    CaseRepository caseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveCase(Case cases) {
        System.out.println(cases);
      caseRepository.save(cases);
    }

    @Override
    public List<Case> getAllByUser(String name) {
        long id = userRepository.getUserByUsername(name).getId();
        return caseRepository.getAllTasksByUserId(id);
    }
}
