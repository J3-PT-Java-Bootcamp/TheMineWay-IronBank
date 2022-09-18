package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.CreateCheckingDTO;
import com.themineway.themineway_ironbank.dto.accounts.TransferenceDTO;
import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.NotFoundException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "checking-account")
public class CheckingController implements IAccountController<Checking, CreateCheckingDTO> {
    @Autowired
    CheckingService checkingService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
        @Validated @RequestBody() CreateCheckingDTO checkingDTO
    ) {
        checkingService.createChecking(checkingDTO.toChecking());
    }

    @PatchMapping("{id}/update-balance")
    public void updateBalance(
        @PathVariable(name = "id") int accountId,
        @Validated @RequestBody UpdateAccountBalanceDTO updateAccountBalanceDTO
    ) {
        checkingService.updateAccountBalance(accountId, updateAccountBalanceDTO.balance);
    }

    @GetMapping("{id}")
    public Checking get(
        @PathVariable(name = "id") int id
    ) {
        return checkingService.getById(id);
    }

    @GetMapping("my-accounts")
    public List<Checking> getMyAccount(
        Principal principal
    ) {
        return checkingService.getAccountsByKeycloakUser(principal.getName());
    }

    @GetMapping("my-account/{id}")
    public Checking getMyAccount(
        Principal principal,
        @PathVariable(name = "id") int id
    ) {
        final var account = checkingService.getAccountByKeycloakUser(principal.getName(), id);
        if(account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return account.get();
    }

    @PostMapping("transfer")
    public void transfer(
        @Validated @RequestBody TransferenceDTO transferenceDTO,
        Principal principal
    ) {
        checkingService.transfer(transferenceDTO, principal.getName());
    }
}
