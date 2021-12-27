package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.Person;
import com.sadsirko.lab23.model.entity.Reader;
import com.sadsirko.lab23.model.service.PersonService;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.seviceImp.PersonServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostRegisterAction implements Action {
    private final PersonService personService = new PersonServiceImpl() ;
    private static final int USER_ROLE_ID = 2;
    private static final Boolean READER_STATUS = Boolean.TRUE;
    private final ReaderService readerService = new ReaderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        Person person = new Person();
        person.setEmail(request.getParameter("email"));
        person.setPassword(request.getParameter("password"));
        person.setRoleId(USER_ROLE_ID);
        personService.validateRegister(person);
        personService.register(person);
        Reader reader = new Reader();
        reader.setStatus(READER_STATUS);
        reader.setBalance(0);
        reader.setName("Ivan");
        reader.setPersonId(personService.getByEmail(person.getEmail()).getId());
        readerService.addReader(reader);

        return "redirect:/login";

    }
}
