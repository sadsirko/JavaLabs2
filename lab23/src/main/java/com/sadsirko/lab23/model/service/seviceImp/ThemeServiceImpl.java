package com.sadsirko.lab23.model.service.seviceImp;

import com.sadsirko.lab23.model.dao.ThemeDAO;
import com.sadsirko.lab23.model.entity.Theme;
import com.sadsirko.lab23.model.service.ThemeService;

import java.util.List;

public class ThemeServiceImpl implements ThemeService {
    ThemeDAO themeDao = new ThemeDAO();
    @Override
    public List<Theme> findAll() {
        return themeDao.findAll();
    }

    @Override
    public Theme find(int id) {
        return themeDao.find(id);
    }

    @Override
    public Theme findByName(String name) {
        return themeDao.findByName(name);
    }
}
