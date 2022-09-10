package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.repository.accounts.CheckingRepository;
import com.themineway.themineway_ironbank.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    UserRepository userRepository;

    public void createChecking(Checking checking) {
        final var primaryOwner = userRepository.findById(checking.getPrimaryOwner().getId());
        if(primaryOwner.isEmpty()) {
            System.out.println("Not present");
            return;
        }
        System.out.println(primaryOwner.get().getName());
        checkingRepository.save(checking);
    }

}