package com.themineway.themineway_ironbank.controller;

import com.themineway.themineway_ironbank.dto.accounts.UpdateAccountBalanceDTO;

public interface IAccountController<Entity, CreateDTO> {
    void create(CreateDTO dto);
    void updateBalance(int id, UpdateAccountBalanceDTO updateAccountBalanceDTO);
    Entity get(int id);
}
