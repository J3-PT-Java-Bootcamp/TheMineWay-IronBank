package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.CreateSavingsDTO;
import com.themineway.themineway_ironbank.dto.accounts.TransferenceDTO;
import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.Savings;
import com.themineway.themineway_ironbank.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "savings-account")
public class SavingsController implements IAccountController<Savings, CreateSavingsDTO> {

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
        savingsService.updateAccountBalance(accountId, new Money(new BigDecimal(updateAccountBalanceDTO.balance)));
    }

    @GetMapping("{id}")
    public Savings get(
        @PathVariable(name = "id") int id
    ) {
        return savingsService.getById(id);
    }

    @GetMapping("my-accounts")
    public List<Savings> getMyAccount(
        Principal principal
    ) {
        return savingsService.getAccountsByKeycloakUser(principal.getName());
    }

    @GetMapping("my-account/{id}")
    public Savings getMyAccount(
        Principal principal,
        @PathVariable(name = "id") int id
    ) {
        final var account = savingsService.getAccountByKeycloakUser(principal.getName(), id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return account.get();
    }

    @PostMapping("transfer")
    public void transfer(
        @Validated @RequestBody TransferenceDTO transferenceDTO,
        Principal principal
    ) {
        savingsService.transfer(transferenceDTO, principal.getName());
    }
}
