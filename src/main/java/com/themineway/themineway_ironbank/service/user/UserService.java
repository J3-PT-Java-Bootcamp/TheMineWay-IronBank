package com.themineway.themineway_ironbank.service.user;

import com.themineway.themineway_ironbank.model.users.User;
import com.themineway.themineway_ironbank.model.users.UserType;
import com.themineway.themineway_ironbank.repository.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user, UserType userType) {
        user.setType(userType);
        userRepository.save(user);
    }
}