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
@SQLDelete(sql = "UPDATE Savings SET deletedAt = SYSDATE() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Savings extends BaseAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    Float interestRate;

    @ManyToOne
    @JoinColumn(nullable = false)
    User primaryOwner;

    @ManyToOne
    @JoinColumn(nullable = true)
    User secondaryOwner;

    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount", nullable = false)),
        @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency", nullable = false))
    })
    @Embedded
    Money minimumBalance;

    @Column
    Date lastInterest;

    // Timestamps

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    @Column
    private Date deletedAt;
}