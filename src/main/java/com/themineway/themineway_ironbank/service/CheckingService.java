package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.repository.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    public void createChecking(Checking checking) {
        checkingRepository.save(checking);
    }

}