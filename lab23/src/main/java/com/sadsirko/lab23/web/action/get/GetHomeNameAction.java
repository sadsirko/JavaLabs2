package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.model.entity.Person;
import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Reader;
import com.sadsirko.lab23.model.entity.Theme;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.ThemeService;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ThemeServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class GetHomeNameAction implements Action {
    private final PrintCenterService printCenterService = new PrintCenterServiceImpl();
    private final ReaderService readerService = new ReaderServiceImpl();
    private final ThemeService themeService = new ThemeServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        Person person = (Person) request.getSession().getAttribute("person");
        if (person != null) {
            Reader reader = readerService.findByPersonId(person.getId());
            request.setAttribute("reader", reader);
        }

        List<Theme> themeList = themeService.findAll();
        List<PrintCenter> printCenterList = new ArrayList<>();
        printCenterList.add(printCenterService.findByName(request.getParameter("name")));
        request.setAttribute("printCenterList", printCenterList);
        request.setAttribute("themeList", themeList);

        return "/home";
    }
}
