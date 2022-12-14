package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM checking sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL")
    List<Checking> getAccountsByKeycloakUserId(String userId);

    @Query(nativeQuery = true, value = "SELECT * FROM checking sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL AND sc.id = :accountId")
    Optional<Checking> getAccountByKeycloakUserId(String userId, int accountId);

    @Query(nativeQuery = true, value = "SELECT ROUND(TIMESTAMPDIFF(DAY, IFNULL(c.last_monthly_fee, c.created_at) , CURRENT_TIMESTAMP())*12/365.24) as 'months' FROM checking c WHERE c.id = :id")
    Optional<Map<String, Object>> getMonthsSinceLastMonthlyFee(int id);
}