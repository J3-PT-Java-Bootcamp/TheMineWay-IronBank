package com.themineway.themineway_ironbank.controller;


import com.themineway.themineway_ironbank.dto.accounts.CreateCheckingDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/account")
public class AccountController {

    @PostMapping("/new-checking")
    Checking createCheching(
        @Validated @RequestBody() CreateCheckingDTO checking
    ) {
        return checking.toChecking();
    }
}
