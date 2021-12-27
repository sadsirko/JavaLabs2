package com.sadsirko.lab23.model.service;

import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription find(int id);
    List<PrintCenter> getNames(int id);
    List<Subscription> findByReaderId(int id);
    void delete(int printCenterId);
    void addSubscripption(Subscription subscription);
}
