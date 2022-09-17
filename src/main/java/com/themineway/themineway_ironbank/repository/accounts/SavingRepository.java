package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingRepository extends JpaRepository<Savings, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM savings sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL")
    List<Savings> getAccountsByKeycloakUserId(String userId);

    @Query(nativeQuery = true, value = "SELECT * FROM savings sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL AND sc.id = :accountId")
    Optional<Savings> getAccountByKeycloakUserId(String userId, int accountId);
}