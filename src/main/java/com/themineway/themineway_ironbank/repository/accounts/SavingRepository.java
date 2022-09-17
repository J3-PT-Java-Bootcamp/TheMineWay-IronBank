package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingRepository extends JpaRepository<Savings, Integer> {
    @Query(nativeQuery = true, value = "SELECT id, account_status, balance_amount, balance_currency, penalty_fee, secret_key, created_at, deleted_at, updated_at, primary_owner_id, secondary_owner_id FROM savings sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL")
    List<Savings> getAccountsByKeycloakUserId(String userId);
}