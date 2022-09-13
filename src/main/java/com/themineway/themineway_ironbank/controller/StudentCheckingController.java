package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "student-checking-account")
public class StudentCheckingController {
    @Autowired
    StudentCheckingService studentCheckingService;
}
