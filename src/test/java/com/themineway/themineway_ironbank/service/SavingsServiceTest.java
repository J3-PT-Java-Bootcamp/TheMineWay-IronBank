package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.AccountStatus;
import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.Savings;
import com.themineway.themineway_ironbank.repository.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavingsServiceTest {

    @Autowired
    SavingsService service;

    @Autowired
    UserRepository userRepository;

    @Test
    void createSavings() {
        final var account = new Savings();
        final var user = userRepository.findAll().get(0);

        account.setBalance(new Money(new BigDecimal("15000")));
        account.setPenaltyFee(50);
        account.setInterestRate(16.2f);
        account.setPenaltyFee(2);
        account.setSecretKey("secretantonio");
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setMinimumBalance(new Money(new BigDecimal("100")));

        account.setPrimaryOwner(user);

        assertAll(() -> {
            service.createSavings(account);
        });
    }
}