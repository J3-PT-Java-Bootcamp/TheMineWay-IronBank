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
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency", nullable = false))
    })
    @Embedded
    Money balance;

    @Column
    String secretKey;

    // Using BigDecimal may be too overkilling
    @Column(nullable = false)
    int penaltyFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AccountStatus accountStatus;
}
