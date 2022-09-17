package com.themineway.themineway_ironbank.service.auth;

import com.themineway.themineway_ironbank.config.security.KeycloakProvider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;

@Component
public class AuthService {

    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;

    public AuthService(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
        this.kcAdminClient = kcAdminClient;
        this.kcProvider = kcProvider;
    }

    public ResponseEntity<AccessTokenResponse> login(String login, String password) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(login, password).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }

}