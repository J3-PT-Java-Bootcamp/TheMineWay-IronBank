package com.themineway.themineway_ironbank.service.auth;

import com.themineway.themineway_ironbank.config.security.KeycloakProvider;
import com.themineway.themineway_ironbank.dto.auth.CreateKeycloakUserDTO;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;


@Service
@Log
public class KeycloakAdminClientService {
    private final KeycloakProvider kcProvider;
    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    public KeycloakAdminClientService(KeycloakProvider keycloakProvider) {
        this.kcProvider = keycloakProvider;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public Response createKeycloakUser(CreateKeycloakUserDTO user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstname());
        kcUser.setLastName(user.getLastname());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

//        Change this to change the group logic
        kcUser.setGroups(List.of("members"));

        Response response = usersResource.create(kcUser);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    .filter(userRep -> userRep.getUsername().equals(kcUser.getUsername())).toList();
            var createdUser = userList.get(0);
            log.info("User with id: " + createdUser.getId() + " created");

//            TODO you may add you logic to store and connect the keycloak user to the local user here

        } else {
            System.out.println("error on creation");
        }

        return response;

    }


}