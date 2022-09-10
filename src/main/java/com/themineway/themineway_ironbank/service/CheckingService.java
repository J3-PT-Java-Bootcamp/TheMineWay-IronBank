package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.CheckingRepository;
import com.themineway.themineway_ironbank.repository.accounts.StudentCheckingRepository;
import com.themineway.themineway_ironbank.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Component
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    UserRepository userRepository;

    public void createChecking(Checking checking) {
        final var primaryOwnerFetch = userRepository.findById(checking.getPrimaryOwner().getId());
        if(primaryOwnerFetch.isEmpty()) return; // TODO: throw exception

        final var primaryOwner = primaryOwnerFetch.get();
        final var age = Period.between(primaryOwner.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();

        if(age < 24) {
            final var studentChecking = new StudentChecking();

            studentChecking.setAccountStatus(checking.getAccountStatus());
            studentChecking.setBalance(checking.getBalance());
            studentChecking.setPenaltyFee(checking.getPenaltyFee());
            studentChecking.setSecretKey(checking.getSecretKey());
            studentChecking.setPrimaryOwner(checking.getPrimaryOwner());

            studentCheckingRepository.save(studentChecking);
        } else {
            checkingRepository.save(checking);
        }
    }

}