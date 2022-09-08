package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}