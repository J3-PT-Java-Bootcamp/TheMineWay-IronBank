package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.dto.accounts.TransferenceDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.Savings;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SavingsService {

    @Autowired
    SavingRepository savingRepository;

    public List<Savings> getAllSavings() {
        return savingRepository.findAll();
    }

    public void createSavings(Savings savings) {

        float interestRate = savings.getInterestRate() == null ? 0.0025f : savings.getInterestRate();
        Money minimumBalance = savings.getMinimumBalance() == null ? new Money(new BigDecimal("1000")) : savings.getMinimumBalance();

        savings.setInterestRate(interestRate);
        savingRepository.save(savings);
    }

    public void updateAccountBalance(int id, Money balance) {
        final var _account = savingRepository.findById(id);
        if(_account.isEmpty()) return; // TODO: throw error
        final var account = _account.get();
        account.setBalance(balance);
        savingRepository.save(account);
    }

    public Savings getById(int id) {
        final var _savings = savingRepository.findById(id);
        if(_savings.isEmpty()) return null; //TODO: except
        return _savings.get();
    }

    public List<Savings> getAccountsByKeycloakUser(String userId) {
        return savingRepository.getAccountsByKeycloakUserId(userId);
    }

    public Optional<Savings> getAccountByKeycloakUser(String userId, int accountId) {
        return savingRepository.getAccountByKeycloakUserId(userId, accountId);
    }

    public void transfer(TransferenceDTO transferenceDTO, String owner) {
        final var fromAccount = savingRepository.getAccountByKeycloakUserId(owner, transferenceDTO.from);
        final var toAccount = savingRepository.findById(transferenceDTO.to);

        if(fromAccount.isEmpty() || toAccount.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        final var from = fromAccount.get();
        final var to = toAccount.get();

        if(from.getBalance().getAmount().compareTo(transferenceDTO.amount) < 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        from.getBalance().decreaseAmount(transferenceDTO.amount);
        to.getBalance().increaseAmount(transferenceDTO.amount);

        savingRepository.saveAll(Arrays.stream(new Savings[] { from, to }).toList());
        applyPenalty(from);
    }

    public void applyPenalty(Savings account) {
        if(account.getMinimumBalance().getAmount().compareTo(account.getBalance().getAmount()) > 0) {
            account.getBalance().decreaseAmount(new BigDecimal(account.getPenaltyFee()));
            savingRepository.save(account);
        }
    }
}
