package com.themineway.themineway_ironbank.dto.accounts;

import com.themineway.themineway_ironbank.model.accounts.Money;

import javax.validation.constraints.NotNull;

public class UpdateAccountBalanceDTO {
    @NotNull
    public int balance;
}
