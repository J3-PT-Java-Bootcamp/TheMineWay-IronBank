package com.themineway.themineway_ironbank.dto.users;

import com.themineway.themineway_ironbank.model.users.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAdminDTO {
    @NotNull
    @NotBlank
    public String name;

    public User toUser() {
        final var user = new User();

        user.setName(name);

        return user;
    }
}