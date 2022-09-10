package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Savings, Integer> {
}