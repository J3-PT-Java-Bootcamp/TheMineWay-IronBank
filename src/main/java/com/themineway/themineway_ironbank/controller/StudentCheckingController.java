package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.NotFoundException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "student-checking-account")
public class StudentCheckingController implements IAccountController<StudentChecking, Object> {
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
    
    @GetMapping("{id}")
    public StudentChecking get(
        @PathVariable(name = "id") int id
    ) {
        return studentCheckingService.getById(id);
    }

    @GetMapping("my-accounts")
    public List<StudentChecking> getMyAccount(
        Principal principal
    ) {
        System.out.println(principal.getName());
        return studentCheckingService.getAccountsByKeycloakUser(principal.getName());
    }

    @GetMapping("my-account/{id}")
    public StudentChecking getMyAccount(
            Principal principal,
            @PathVariable(name = "id") int id
    ) {
        final var account = studentCheckingService.getAccountByKeycloakUser(principal.getName(), id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return account.get();
    }
}
