package com.themineway.themineway_ironbank.model.accounts;

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
@Where(clause = "deletedAt IS NULL")
public class Savings extends BaseAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Timestamps

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    @Column
    private Date deletedAt;
}