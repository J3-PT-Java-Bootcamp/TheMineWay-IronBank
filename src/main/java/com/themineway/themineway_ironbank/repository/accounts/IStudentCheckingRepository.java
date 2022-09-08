package com.themineway.themineway_ironbank.repository.accounts;

import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
}
