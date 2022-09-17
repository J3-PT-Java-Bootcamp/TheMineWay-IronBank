package com.themineway.themineway_ironbank.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateKeycloakUserDTO {
    public String username;
    public String password;
    public String email;
    public String firstname;
    public String lastname;
}
