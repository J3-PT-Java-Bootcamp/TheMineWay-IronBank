package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;
import com.themineway.themineway_ironbank.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "student-checking-account")
public class StudentCheckingController implements IAccountController<Object> {
    @Autowired
    StudentCheckingService studentCheckingService;

    @Override
    public void create(Object o) { }

    @PatchMapping("{id}/update-balance")
    public void updateBalance(
        @PathVariable(name = "id") int accountId,
        @Validated @RequestBody UpdateAccountBalanceDTO updateAccountBalanceDTO
    ) {
        studentCheckingService.updateAccountBalance(accountId, updateAccountBalanceDTO.balance);
    }
}
