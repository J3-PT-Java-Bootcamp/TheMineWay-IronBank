package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.CreateSavingsDTO;
import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;
import com.themineway.themineway_ironbank.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "savings-account")
public class SavingsController implements IAccountController<CreateSavingsDTO> {

    @Autowired
    SavingsService savingsService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @Validated @RequestBody() CreateSavingsDTO savingsDTO
    ) {
        savingsService.createSavings(savingsDTO.toSavings());
    }
    @PatchMapping("{id}/update-balance")
    public void updateBalance(
            @PathVariable(name = "id") int accountId,
            @Validated @RequestBody UpdateAccountBalanceDTO updateAccountBalanceDTO
    ) {
        savingsService.updateAccountBalance(accountId, updateAccountBalanceDTO.balance);
    }
}
