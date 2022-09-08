package com.themineway.themineway_ironbank.dto.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAdminDTO {
    @NotNull
    @NotBlank
    String name;
}