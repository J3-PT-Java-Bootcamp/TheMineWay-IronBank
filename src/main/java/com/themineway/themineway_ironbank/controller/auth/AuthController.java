package com.themineway.themineway_ironbank.controller.auth;

import com.themineway.themineway_ironbank.dto.auth.LoginDTO;
import com.themineway.themineway_ironbank.service.auth.AuthService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @CrossOrigin()
    @PostMapping(path = "login")
    public ResponseEntity<AccessTokenResponse> login(
        @Validated @RequestBody LoginDTO loginDTO
    ) {
        return authService.login(loginDTO.login, loginDTO.password);
    }
}