package com.themineway.themineway_ironbank.model.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseAccount {
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    @Embedded
    Money balance;

    @Column
    String secretKey;

    // TODO: maybe change to object
    @Column
    String primaryOwner;

    // Nullable
    @Column(nullable = true)
    String secondaryOwner;

    // Using BigDecimal may be too overkilling
    @Column
    int penaltyFee;

    @Enumerated(EnumType.STRING)
    @Column
    AccountStatus accountStatus;
}
