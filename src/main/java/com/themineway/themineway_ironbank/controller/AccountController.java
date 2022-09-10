package com.themineway.themineway_ironbank.controller;


import com.themineway.themineway_ironbank.dto.accounts.CreateCheckingDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "account")
public class AccountController {

    @GetMapping
    String getHello() {
        return "Hello";
    }

    @PostMapping("/new-checking")
    Checking createCheching(
        @Validated @RequestBody() CreateCheckingDTO checking
    ) {
        return checking.toChecking();
    }
}
