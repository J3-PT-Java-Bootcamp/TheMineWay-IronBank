package com.themineway.themineway_ironbank.dto.accounts;

import com.themineway.themineway_ironbank.model.accounts.AccountStatus;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.users.User;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateCheckingDTO {

    @NotNull
    public AccountStatus accountStatus;
    @NotNull
    public BigDecimal balanceAmount;
    @NotNull
    public int penaltyFee;
    @NotNull
    public int primaryOwner;
    public Integer secondaryOwner;
    @NotNull
    public String secretKey;
    @NotNull
    public BigDecimal minimumBalanceAmount;
    @NotNull
    public int monthlyMaintenanceFee;

    public Checking toChecking() {
        final var checking = new Checking();
        checking.setAccountStatus(accountStatus);
        checking.setBalance(new Money(balanceAmount));
        checking.setPenaltyFee(penaltyFee);
        checking.setPrimaryOwner(new User(primaryOwner));
        if(secondaryOwner != null) checking.setSecondaryOwner(new User(secondaryOwner));
        checking.setSecretKey(secretKey);
        checking.setMinimumBalance(new Money(minimumBalanceAmount));
        checking.setMonthlyMaintenanceFee(monthlyMaintenanceFee);

        return checking;
    }
}