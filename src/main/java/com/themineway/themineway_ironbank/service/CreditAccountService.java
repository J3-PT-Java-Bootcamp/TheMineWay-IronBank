package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.repository.accounts.CreditAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreditAccountService {

    @Autowired
    CreditAccountRepository creditAccountRepository;

    public List<CreditAccount> getAllCreditAccounts() {
        return creditAccountRepository.findAll();
    }

    public void createCreditAccount(CreditAccount creditAccount) {

        BigDecimal creditLimit = creditAccount.getCreditLimit() == null ? new BigDecimal("100") : creditAccount.getCreditLimit();
        float interestRate = creditAccount.getInterestRate() == null ? 0.2f : creditAccount.getInterestRate();

        creditAccount.setCreditLimit(creditLimit);
        creditAccount.setInterestRate(interestRate);

        creditAccountRepository.save(creditAccount);
    }

    public void updateAccountBalance(int id, Money balance) {
        final var _account = creditAccountRepository.findById(id);
        if(_account.isEmpty()) return; // TODO: throw error
        final var account = _account.get();
        account.setBalance(balance);
        creditAccountRepository.save(account);
    }

}