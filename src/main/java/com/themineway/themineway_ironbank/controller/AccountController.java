package com.themineway.themineway_ironbank.controller;


import com.themineway.themineway_ironbank.dto.accounts.CreateCheckingDTO;
import com.themineway.themineway_ironbank.dto.accounts.CreateCreditAccountDTO;
import com.themineway.themineway_ironbank.dto.accounts.CreateSavingsDTO;
import com.themineway.themineway_ironbank.service.CheckingService;
import com.themineway.themineway_ironbank.service.CreditAccountService;
import com.themineway.themineway_ironbank.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    @Autowired
    CheckingService checkingService;

    @Autowired
    SavingsService savingsService;

    @Autowired
    CreditAccountService creditAccountService;

    // ADMINS ONLY
    @PostMapping("new-checking")
    @ResponseStatus(HttpStatus.CREATED)
    void createChecking(
        @Validated @RequestBody() CreateCheckingDTO checkingDTO
    ) {
        checkingService.createChecking(checkingDTO.toChecking());
    }

    // ADMINS ONLY
    @PostMapping("new-savings")
    @ResponseStatus(HttpStatus.CREATED)
    void createSavings(
        @Validated @RequestBody() CreateSavingsDTO savingsDTO
    ) {
        savingsService.createSavings(savingsDTO.toSavings());
    }

    // ADMINS ONLY
    @PostMapping("new-credit-account")
    @ResponseStatus(HttpStatus.CREATED)
    void createCreditAccount(
        @Validated @RequestBody()CreateCreditAccountDTO creditAccountDTO
    ) {
        creditAccountService.createCreditAccount(creditAccountDTO.toCreditAccount());
    }
}