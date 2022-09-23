package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.AccountStatus;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.users.User;
import com.themineway.themineway_ironbank.repository.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckingServiceTest {

    @Autowired
    CheckingService service;

    @Autowired
    UserRepository userRepository;

    @Test
    void createChecking() {
        final var account = new Checking();
        final var user = userRepository.findAll().get(0);

        account.setBalance(new Money(new BigDecimal("15000")));
        account.setSecretKey("secret_paco");
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setPenaltyFee(50);

        account.setPrimaryOwner(user);

        assertAll(() -> {
            service.createChecking(account);
        });
    }
}