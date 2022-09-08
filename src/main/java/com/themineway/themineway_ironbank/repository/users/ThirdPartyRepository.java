package com.themineway.themineway_ironbank.repository.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyRepository implements IUserRepository {

    @Autowired
    private UserRepository userRepository;
}