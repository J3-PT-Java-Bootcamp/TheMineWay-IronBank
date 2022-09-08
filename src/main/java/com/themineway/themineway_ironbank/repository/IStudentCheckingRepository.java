package com.themineway.themineway_ironbank.repository;

import com.themineway.themineway_ironbank.model.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
}
