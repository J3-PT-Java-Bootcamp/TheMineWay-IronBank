package com.themineway.themineway_ironbank.dto.users;

import com.themineway.themineway_ironbank.model.users.Address;
import com.themineway.themineway_ironbank.model.users.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateAccountHolderDTO {
    @NotNull
    @NotBlank
    public String name;

    @NotNull
    @NotBlank
    public Date birthDate;

    @NotNull
    @NotBlank
    public Address primaryAddress;

    @Email
    public String mailingAddress;

    public User toUser() {
        final var user = new User();

        user.setName(name);
        user.setBirthDate(birthDate);
        user.setPrimaryAddress(primaryAddress);
        user.setMailAddress(mailingAddress);

        return user;
    }
}