package com.themineway.themineway_ironbank.dto.users;

import com.themineway.themineway_ironbank.model.users.Address;
import com.themineway.themineway_ironbank.model.users.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateAccountHolderDTO {
    @NotNull
    @Length(min = 2)
    public String name;

    @NotNull
    public Date birthDate;

    @NotNull
    public Integer addressNumber;

    @NotNull
    public String addressStreet;

    @NotNull
    public String addressCountryCode;

    @Email
    public String mailingAddress;

    @NotNull
    @Length(min = 8, max = 128)
    public String password;

    public User toUser() {
        final var user = new User();
        final var address = new Address();
        address.street = addressStreet;
        address.countryCode = addressCountryCode;
        address.number = addressNumber;

        user.setName(name);
        user.setBirthDate(birthDate);
        user.setPrimaryAddress(address);
        user.setMailAddress(mailingAddress);
        user.setPassword(password);

        return user;
    }
}