package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
    @Query(nativeQuery = true, value = "SELECT id, account_status, balance_amount, balance_currency, penalty_fee, secret_key, created_at, deleted_at, updated_at, primary_owner_id, secondary_owner_id FROM checking sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL")
    List<Checking> getAccountsByKeycloakUserId(String userId);
}