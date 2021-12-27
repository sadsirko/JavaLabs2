package com.sadsirko.lab23.model.service.seviceImp;

import com.sadsirko.lab23.model.dao.PersonDAO;
import com.sadsirko.lab23.model.entity.Person;
import com.sadsirko.lab23.model.exception.ServiceException;
import com.sadsirko.lab23.model.service.PersonService;

public class PersonServiceImpl implements PersonService {
    private final PersonDAO personDao = new PersonDAO();
    @Override
    public void validateRegister(Person person) {
        if (personDao.findByEmail(person.getEmail()) != null){
            throw new ServiceException("Account with such email already exists");
        }
    }

    @Override
    public void register(Person person) {
        personDao.save(person);
    }

    @Override
    public Person getByEmail(String email) {
        return personDao.findByEmail(email);
    }


    @Override
    public Person login(String email, String password) {
        Person person = personDao.findByEmailAndPassword(email,password);
        if ( person == null){
            throw new ServiceException("No user with such email and password");
        }
        return person;
    }
}
