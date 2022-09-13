package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.service.CheckingService;
import com.themineway.themineway_ironbank.service.CreditAccountService;
import com.themineway.themineway_ironbank.service.SavingsService;
import com.themineway.themineway_ironbank.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    @Autowired CheckingService checkingService;
    @Autowired SavingsService savingsService;
    @Autowired CreditAccountService creditAccountService;
    @Autowired StudentCheckingService studentCheckingService;

    // ADMINS ONLY
    @GetMapping("all-accounts")
    Map<String, List> getAllAccounts() {
        return Map.of(
            "checkings", checkingService.getAllCheckings(),
            "studentCheckings", studentCheckingService.getAllStudentCheckings(),
            "savings", savingsService.getAllSavings(),
            "creditAccounts", creditAccountService.getAllCreditAccounts()
        );
    }
}