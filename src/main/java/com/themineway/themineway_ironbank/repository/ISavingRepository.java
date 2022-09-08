package com.themineway.themineway_ironbank.repository;

import com.themineway.themineway_ironbank.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISavingRepository extends JpaRepository<Savings, Integer> {
}
