package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.Savings;
import com.themineway.themineway_ironbank.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SavingsService {

    @Autowired
    SavingRepository savingRepository;

    public List<Savings> getAllSavings() {
        return savingRepository.findAll();
    }

    public void createSavings(Savings savings) {

        float interestRate = savings.getInterestRate() == null ? 0.0025f : savings.getInterestRate();

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
}
