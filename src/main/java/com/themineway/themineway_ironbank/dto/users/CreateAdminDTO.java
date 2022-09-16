package com.themineway.themineway_ironbank.dto.users;

import com.themineway.themineway_ironbank.model.users.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAdminDTO {
    @NotNull
    @NotBlank
    @Length(min = 2)
    public String name;

    public User toUser() {
        final var user = new User();

        user.setName(name);

        return user;
    }
}