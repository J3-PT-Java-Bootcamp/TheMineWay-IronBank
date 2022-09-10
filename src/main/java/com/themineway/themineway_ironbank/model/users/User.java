package com.themineway.themineway_ironbank.model.users;

import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class User {

    public User(int id) {
        setId(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;

    @Column
    Date birthDate;

    @Embedded
    Address primaryAddress;

    @Column
    String mailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserType type;

    // Timestamps

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    // Associations

    @OneToMany(mappedBy = "primaryOwner")
    List<CreditAccount> primaryCreditAccounts;
}