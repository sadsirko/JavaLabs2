package com.sadsirko.lab23.model.service;

import com.sadsirko.lab23.model.entity.PrintCenter;

import java.util.List;

public interface PrintCenterService {
    PrintCenter find(int id);

    PrintCenter findByName(String name);

    List<PrintCenter> findAllSortedName();

    List<PrintCenter> findAllSortedPriceMin();

    List<PrintCenter> findAllSortedSortedPriceMax();

    void delete(int id);

    void save(PrintCenter printCenter);

    void update(PrintCenter printCenter);
}
