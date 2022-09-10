package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.accounts.Savings;
import com.themineway.themineway_ironbank.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavingsService {

    @Autowired
    SavingRepository savingRepository;

    public void createSavings(Savings savings) {

        float interestRate = savings.getInterestRate() == null ? 0.0025f : savings.getInterestRate();

        savings.setInterestRate(interestRate);
        savingRepository.save(savings);
    }
}
