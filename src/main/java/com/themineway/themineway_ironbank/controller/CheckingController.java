package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.CreateCheckingDTO;
import com.themineway.themineway_ironbank.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "checking-account")
public class CheckingController implements IAccountController<CreateCheckingDTO> {
    @Autowired
    CheckingService checkingService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
        @Validated @RequestBody() CreateCheckingDTO checkingDTO
    ) {
        checkingService.createChecking(checkingDTO.toChecking());
    }
}
