package com.themineway.themineway_ironbank.model.users;

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
@SQLDelete(sql = "UPDATE User SET deletedAt = SYSDATE() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
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

    @Column
    String password;

    @Column
    String login;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserType type;

    @Column(nullable = false)
    String keycloakUserId;

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