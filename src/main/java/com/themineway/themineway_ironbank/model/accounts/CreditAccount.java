package com.themineway.themineway_ironbank.model.accounts;

import com.themineway.themineway_ironbank.model.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class CreditAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency", nullable = false))
    })
    @Embedded
    Money balance;

    @ManyToOne
    @JoinColumn(nullable = true)
    User primaryOwner;

    @ManyToOne
    @JoinColumn(nullable = true)
    User secondaryOwner;

    @Column(nullable = false)
    BigDecimal creditLimit;

    @Column(nullable = false)
    Float interestRate;

    // Using BigDecimal may be too overkilling
    @Column(nullable = false)
    int penaltyFee;

    // Timestamps

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

}
