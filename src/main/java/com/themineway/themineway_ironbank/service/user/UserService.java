package com.themineway.themineway_ironbank.service.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.themineway.themineway_ironbank.model.users.User;
import com.themineway.themineway_ironbank.model.users.UserType;
import com.themineway.themineway_ironbank.repository.users.*;
import com.themineway.themineway_ironbank.service.auth.KeycloakAdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    public void createUser(User user, UserType userType) {
        user.setType(userType);

        try {
            FirebaseAuth fAuth = FirebaseAuth.getInstance();
            final var fUser = new UserRecord.CreateRequest();
            fUser.setEmail(user.getMailAddress());
            fUser.setPassword(user.getPassword());
            fUser.setDisplayName(user.getName());
            fAuth.createUser(fUser);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        user.setKeycloakUserId("1");
        userRepository.save(user);
    }
}