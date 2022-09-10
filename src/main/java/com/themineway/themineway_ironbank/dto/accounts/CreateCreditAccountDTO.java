package com.themineway.themineway_ironbank.dto.accounts;

import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import com.themineway.themineway_ironbank.model.accounts.Money;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateCreditAccountDTO {

    @NotNull
    public BigDecimal balanceAmount;

    @NotNull
    @NotBlank
    public String primaryOwner;

    public String secondaryOwner;

    @DecimalMin("0")
    @DecimalMax("100000")
    public BigDecimal creditLimit;

    @DecimalMin("0.1")
    public Float interestRate;

    @NotNull
    @DecimalMin("0")
    public int penaltyFee;

    public CreditAccount toCreditAccount() {
        final var creditAccount = new CreditAccount();

        creditAccount.setBalance(new Money(balanceAmount));
        creditAccount.setPrimaryOwner(primaryOwner);
        creditAccount.setSecondaryOwner(secondaryOwner);
        creditAccount.setCreditLimit(creditLimit);
        creditAccount.setInterestRate(interestRate);
        creditAccount.setPenaltyFee(penaltyFee);

        return creditAccount;
    }

}