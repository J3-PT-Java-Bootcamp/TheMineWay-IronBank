package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.CreateCreditAccountDTO;
import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;
import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import com.themineway.themineway_ironbank.service.CreditAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "credit-account")
public class CreditController implements IAccountController<CreditAccount, CreateCreditAccountDTO> {

    @Autowired
    CreditAccountService creditAccountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Validated @RequestBody() CreateCreditAccountDTO creditAccountDTO
    ) {
        creditAccountService.createCreditAccount(creditAccountDTO.toCreditAccount());
    }

    @PatchMapping("{id}/update-balance")
    public void updateBalance(
            @PathVariable(name = "id") int accountId,
            @Validated @RequestBody UpdateAccountBalanceDTO updateAccountBalanceDTO
    ) {
        creditAccountService.updateAccountBalance(accountId, updateAccountBalanceDTO.balance);
    }

    @GetMapping("{id}")
    public CreditAccount get(
            @PathVariable(name = "id") int id
    ) {
        return creditAccountService.getById(id);
    }
}
