package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM student_checking sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL")
    List<StudentChecking> getAccountsByKeycloakUserId(String userId);

    @Query(nativeQuery = true, value = "SELECT * FROM student_checking sc WHERE sc.primary_owner_id IN (SELECT u.id FROM `user` u WHERE u.keycloak_user_id = :userId AND u.deleted_at IS NULL) AND sc.deleted_at IS NULL AND sc.id = :accountId")
    Optional<StudentChecking> getAccountByKeycloakUserId(String userId, int accountId);
}