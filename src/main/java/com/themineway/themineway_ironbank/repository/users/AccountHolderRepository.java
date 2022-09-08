package com.themineway.themineway_ironbank.repository.users;

import com.themineway.themineway_ironbank.dto.users.CreateAccountHolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountHolderRepository implements IUserRepository<CreateAccountHolderDTO> {

    @Autowired
    private UserRepository userRepository;
}