package com.themineway.themineway_ironbank.service.user;

import com.themineway.themineway_ironbank.dto.auth.CreateKeycloakUserDTO;
import com.themineway.themineway_ironbank.model.users.User;
import com.themineway.themineway_ironbank.model.users.UserType;
import com.themineway.themineway_ironbank.repository.users.*;
import com.themineway.themineway_ironbank.service.auth.KeycloakAdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KeycloakAdminClientService keycloakAdminClientService;

    public void createUser(User user, UserType userType) {
        user.setType(userType);

        final var kcUser = new CreateKeycloakUserDTO();
        kcUser.setUsername(user.getLogin());
        kcUser.setPassword(user.getPassword());
        kcUser.setEmail(kcUser.getEmail());
        kcUser.setFirstname(user.getName());

        final var userResult = keycloakAdminClientService.createKeycloakUser(kcUser);
        ResponseEntity.status(userResult.getStatus()).build();
        System.out.println(userResult.getEntity().toString());

        user.setKeycloakUserId("1");
        userRepository.save(user);
    }
}