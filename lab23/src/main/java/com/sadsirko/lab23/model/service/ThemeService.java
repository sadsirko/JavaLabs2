package com.sadsirko.lab23.model.service;

import com.sadsirko.lab23.model.entity.Theme;

import java.util.List;

public interface ThemeService {
    List<Theme> findAll();

    Theme find(int id);

    Theme findByName(String name);
}
