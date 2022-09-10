package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.accounts.CreditAccount;
import com.themineway.themineway_ironbank.repository.accounts.CreditAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditAccountService {

    @Autowired
    CreditAccountRepository creditAccountRepository;

    public void createCreditAccount(CreditAccount creditAccount) {

        BigDecimal creditLimit = creditAccount.getCreditLimit() == null ? new BigDecimal("100") : creditAccount.getCreditLimit();
        float interestRate = creditAccount.getInterestRate() == null ? 0.2f : creditAccount.getInterestRate();

        creditAccount.setCreditLimit(creditLimit);
        creditAccount.setInterestRate(interestRate);

        creditAccountRepository.save(creditAccount);
    }

}