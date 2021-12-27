package com.sadsirko.lab23.model.service;

import com.sadsirko.lab23.model.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role find(int id);
    Role findByName(String name);
}
