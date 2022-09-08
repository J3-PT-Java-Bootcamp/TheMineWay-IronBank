package com.themineway.themineway_ironbank.repository.users;

import com.themineway.themineway_ironbank.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}