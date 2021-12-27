package com.sadsirko.lab23.model.service;

import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Reader;

import java.util.List;

public interface ReaderService {
    List<Reader> findAll();
    Reader find(int id);
    Reader findByPersonId(int id);
    void addReader(Reader reader);
    void changeBalance(int id, int change);
    void changeStatus(int id);
}
