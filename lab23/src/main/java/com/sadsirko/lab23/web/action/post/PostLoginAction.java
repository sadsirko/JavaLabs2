package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.Person;
import com.sadsirko.lab23.model.entity.Role;
import com.sadsirko.lab23.model.service.PersonService;
import com.sadsirko.lab23.model.service.seviceImp.PersonServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostLoginAction implements Action {
    private final PersonService personService = new PersonServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        Person person = personService.login(request.getParameter("email"), request.getParameter("password"));

        session.setAttribute("person", person);
        int personRole = person.getRoleId();
        session.setAttribute("personRole", personRole);

        return "redirect:/home";
    }
}
