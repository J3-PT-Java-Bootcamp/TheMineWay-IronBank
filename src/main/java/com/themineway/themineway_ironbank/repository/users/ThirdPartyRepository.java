package com.themineway.themineway_ironbank.repository.users;

import com.themineway.themineway_ironbank.dto.users.CreateThirdPartyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyRepository implements IUserRepository<CreateThirdPartyDTO> {

    @Autowired
    private UserRepository userRepository;
}