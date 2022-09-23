package com.themineway.themineway_ironbank.controller.user;

import com.themineway.themineway_ironbank.dto.users.CreateAccountHolderDTO;
import com.themineway.themineway_ironbank.dto.users.CreateAdminDTO;
import com.themineway.themineway_ironbank.dto.users.CreateThirdPartyDTO;
import com.themineway.themineway_ironbank.model.users.UserType;
import com.themineway.themineway_ironbank.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("account-holder")
    public void createAccountHolder(
        @Validated @RequestBody CreateAccountHolderDTO createAccountHolderDTO
    ) {
        userService.createUser(createAccountHolderDTO.toUser(), UserType.ACCOUNT_HOLDER);
    }

    @PostMapping("admin")
    public void createAdmin(
        @Validated @RequestBody CreateAdminDTO createAdminDto
    ) {
        userService.createUser(createAdminDto.toUser(), UserType.ADMIN);
    }

    @PostMapping("third-party")
    public void createThirdParty(
        @Validated @RequestBody CreateThirdPartyDTO createThirdPartyDTO
    ) {
        userService.createUser(createThirdPartyDTO.toUser(), UserType.THIRD_PARTY);
    }
}