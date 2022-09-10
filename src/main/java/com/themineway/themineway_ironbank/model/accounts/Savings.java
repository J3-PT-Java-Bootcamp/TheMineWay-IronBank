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
public class Savings extends BaseAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    Float interestRate;

    @ManyToOne
    User primaryOwner;

    @ManyToOne
    @JoinColumn(nullable = true)
    User secondaryOwner;

    // Timestamps

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

}