package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public List<StudentChecking> getAllStudentCheckings() {
        return studentCheckingRepository.findAll();
    }

    public void updateAccountBalance(int id, Money balance) {
        final var _account = studentCheckingRepository.findById(id);
        if(_account.isEmpty()) return; // TODO: throw error
        final var account = _account.get();
        account.setBalance(balance);
        studentCheckingRepository.save(account);
    }
}
