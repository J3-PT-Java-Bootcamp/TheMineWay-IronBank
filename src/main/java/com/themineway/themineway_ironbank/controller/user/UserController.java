package com.themineway.themineway_ironbank.controller.user;

import com.themineway.themineway_ironbank.dto.users.CreateAdminDTO;
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

    @PostMapping("admin")
    public void createAdmin(
        @Validated @RequestBody CreateAdminDTO createAdminDto
    ) {
        userService.createUser(createAdminDto.toUser(), UserType.ADMIN);
    }
}