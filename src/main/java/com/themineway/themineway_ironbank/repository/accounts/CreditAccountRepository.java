package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, Integer> {
    @Query(nativeQuery = true, value = "SELECT id, account_status, balance_amount, balance_currency, penalty_fee, secret_key, created_at, deleted_at, updated_at, primary_owner_id, secondary_owner_id FROM credit_account sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL")
    List<CreditAccount> getAccountsByKeycloakUserId(String userId);
}