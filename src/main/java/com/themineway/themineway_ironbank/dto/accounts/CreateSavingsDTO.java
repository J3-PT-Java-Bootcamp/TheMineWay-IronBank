package com.themineway.themineway_ironbank.dto.accounts;

import com.themineway.themineway_ironbank.model.accounts.AccountStatus;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.Savings;
import com.themineway.themineway_ironbank.model.users.User;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateSavingsDTO {
    @NotNull
    public AccountStatus accountStatus;

    @NotNull
    public BigDecimal balanceAmount;

    @NotNull
    public int penaltyFee;

    @NotNull
    @NotEmpty
    public int primaryOwner;

    public Integer secondaryOwner;

    @NotNull
    @NotEmpty
    public String secretKey;

    @DecimalMax("0.5")
    public Float interestRate;

    public Savings toSavings() {
        final var savings = new Savings();

        savings.setAccountStatus(accountStatus);
        savings.setBalance(new Money(balanceAmount));
        savings.setPenaltyFee(penaltyFee);
        savings.setPrimaryOwner(new User(primaryOwner));
        if(secondaryOwner != null) savings.setSecondaryOwner(new User(secondaryOwner));
        savings.setSecretKey(secretKey);
        savings.setInterestRate(interestRate);

        return savings;
    }
}
