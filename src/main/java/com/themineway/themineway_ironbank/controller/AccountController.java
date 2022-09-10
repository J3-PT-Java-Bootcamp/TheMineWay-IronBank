package com.themineway.themineway_ironbank.controller;


import com.themineway.themineway_ironbank.dto.accounts.CreateCheckingDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    @PostMapping("new-checking")
    Checking createChecking(
        @Validated @RequestBody() CreateCheckingDTO checking
    ) {
        return checking.toChecking();
    }
}