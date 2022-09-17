package com.themineway.themineway_ironbank.dto.auth;

import javax.validation.constraints.NotNull;

public class LoginDTO {
    @NotNull
    public String login;

    @NotNull
    public String password;
}