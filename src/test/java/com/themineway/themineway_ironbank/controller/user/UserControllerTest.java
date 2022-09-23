package com.themineway.themineway_ironbank.controller.user;

import com.themineway.themineway_ironbank.dto.users.CreateAccountHolderDTO;
import com.themineway.themineway_ironbank.dto.users.CreateAdminDTO;
import com.themineway.themineway_ironbank.dto.users.CreateThirdPartyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    void createAccountHolder() {
        final var dto = new CreateAccountHolderDTO();
        dto.name = "Paco";
        dto.birthDate = new Date();
        dto.addressNumber = 15;
        dto.addressStreet = "Calle";
        dto.addressCountryCode = "ES";
        dto.mailingAddress = "email@dominio.com";
        dto.login = "paco";
        dto.password = "Admin1234!";

        assertAll(() -> {
            userController.createAccountHolder(dto);
        });
    }

    @Test
    void createAdmin() {
        final var dto = new CreateAdminDTO();
        dto.name = "Antonio";
        dto.login = "antonio";
        dto.password = "Admin1234!";

        assertAll(() -> {
            userController.createAdmin(dto);
        });
    }

    @Test
    void testCreateThirdParty() {
        final var dto = new CreateThirdPartyDTO();
        dto.name = "María";
        dto.login = "maria";
        dto.password = "Admin1234!";

        assertAll(() -> {
            userController.createThirdParty(dto);
        });
    }
}