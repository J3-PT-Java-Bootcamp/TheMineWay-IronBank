package com.themineway.themineway_ironbank.service;

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
}
