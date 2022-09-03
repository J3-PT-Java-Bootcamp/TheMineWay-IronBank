package com.themineway.themineway_ironbank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
@SQLDelete(sql = "UPDATE Checking SET deletedAt = SYSDATE() WHERE id=?")
@Where(clause = "deletedAt IS NULL")
public class Checking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

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
    @Column
    String secondaryOwner;

    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    @Embedded
    Money minimumBalance;

    // Using BigDecimal may be too overkilling
    @Column
    int penaltyFee;

    @Column
    int monthlyMaintenanceFee;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    @Column
    private Date deletedAt;
}