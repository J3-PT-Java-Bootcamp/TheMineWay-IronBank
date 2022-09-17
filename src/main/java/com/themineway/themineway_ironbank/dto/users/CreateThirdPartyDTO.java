package com.themineway.themineway_ironbank.dto.users;

import com.themineway.themineway_ironbank.model.users.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateThirdPartyDTO {
    @NotNull
    @NotBlank
    @Length(min = 2)
    public String name;

    @NotNull
    @Length(min = 8, max = 128)
    public String password;

    @NotNull
    @Length(min = 3, max = 32)
    public String login;

    public User toUser() {
        final var user = new User();

        user.setName(name);
        user.setPassword(password);
        user.setLogin(login);

        return user;
    }
}