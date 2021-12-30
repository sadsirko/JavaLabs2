package com.sadsirko.lab23.model.service;

import com.sadsirko.lab23.model.entity.Person;

public interface PersonService {
    void validateRegister(Person account);

    void register(Person account);

    Person getByEmail(String email);

    Person login(String email, String password);
}
