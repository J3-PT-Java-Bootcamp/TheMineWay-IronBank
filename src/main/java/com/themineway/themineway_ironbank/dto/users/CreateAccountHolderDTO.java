package com.themineway.themineway_ironbank.dto.users;

import com.themineway.themineway_ironbank.model.users.Address;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateAccountHolderDTO {
    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    Date birthDate;

    @NotNull
    @NotBlank
    Address primaryAddress;

    @Email
    String mailingAddress;
}