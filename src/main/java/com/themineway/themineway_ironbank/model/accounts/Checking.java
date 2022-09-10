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
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class Checking extends BaseAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    @Embedded
    Money minimumBalance;

    @Column
    int monthlyMaintenanceFee;

    @ManyToOne
    User primaryOwner;

    @ManyToOne
    @JoinColumn(nullable = true)
    User secondaryOwner;

    // Timestamps

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;
}