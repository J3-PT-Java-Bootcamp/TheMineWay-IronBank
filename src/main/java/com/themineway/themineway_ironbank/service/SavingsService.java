package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.Savings;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
}
