package com.themineway.themineway_ironbank.dto.accounts;

import com.themineway.themineway_ironbank.model.accounts.AccountStatus;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateCheckingDTO {
    @NotNull
    AccountStatus accountStatus;
    @NotNull
    BigDecimal accountBalance;
    @NotNull
    int penaltyFee;
    @NotNull
    @NotBlank
    String primaryOwner;
    String secondaryOwner;
    @NotNull
    String secretKey;
    @NotNull
    BigDecimal minimumBalanceAmount;
    @NotNull
    int monthlyMaintenanceFee;

    public Checking toChecking() {
        final var checking = new Checking();
        checking.setAccountStatus(accountStatus);
        checking.setBalance(new Money(accountBalance));
        checking.setPenaltyFee(penaltyFee);
        checking.setPrimaryOwner(primaryOwner);
        checking.setSecondaryOwner(secondaryOwner);
        checking.setSecretKey(secretKey);
        checking.setMinimumBalance(new Money(minimumBalanceAmount));
        checking.setMonthlyMaintenanceFee(monthlyMaintenanceFee);

        return checking;
    }
}