package com.themineway.themineway_ironbank.dto.accounts;

import com.themineway.themineway_ironbank.model.accounts.AccountStatus;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateCheckingDTO {

    @NotNull
    public AccountStatus accountStatus;
    @NotNull
    public BigDecimal accountBalance;
    @NotNull
    public int penaltyFee;
    @NotNull
    @NotBlank
    public String primaryOwner;
    public String secondaryOwner;
    @NotNull
    public String secretKey;
    @NotNull
    public BigDecimal minimumBalanceAmount;
    @NotNull
    public int monthlyMaintenanceFee;

    public Checking toChecking() {
        final var checking = new Checking();
        checking.setAccountStatus(accountStatus);
        checking.setBalance(new Money(accountBalance));
        checking.setPenaltyFee(penaltyFee);
        checking.setPrimaryOwner(primaryOwner);
        checking.setSecondaryOwner(secondaryOwner);
        checking.setSecondaryOwner(secondaryOwner);
        checking.setSecretKey(secretKey);
        checking.setMinimumBalance(new Money(minimumBalanceAmount));
        checking.setMonthlyMaintenanceFee(monthlyMaintenanceFee);

        return checking;
    }
}