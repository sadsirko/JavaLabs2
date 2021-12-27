package com.sadsirko.lab23.model.service.seviceImp;

import com.sadsirko.lab23.model.dao.RoleDAO;
import com.sadsirko.lab23.model.entity.Role;
import com.sadsirko.lab23.model.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDAO roleDao = new RoleDAO();
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role find(int id) {
        return roleDao.find(id);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
