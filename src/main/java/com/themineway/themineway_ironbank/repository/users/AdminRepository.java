package com.themineway.themineway_ironbank.repository.users;

import com.themineway.themineway_ironbank.dto.users.CreateAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminRepository implements IUserRepository<CreateAdminDTO> {

    @Autowired
    private UserRepository userRepository;
}