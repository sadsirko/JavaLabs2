package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.Person;
import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Reader;
import com.sadsirko.lab23.model.service.PersonService;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.seviceImp.PersonServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.web.action.ActionException;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.model.entity.Theme;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PostEditPrintCenterAction implements Action {
    private final PrintCenterService printCenterService = new PrintCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {



        PrintCenter printCenter = new PrintCenter();
        printCenter.setName(request.getParameter("name"));
        printCenter.setPrice(Integer.parseInt(request.getParameter("price")));
        printCenter.setThemeId(Integer.parseInt(request.getParameter("themes")));
        printCenter.setId(Integer.parseInt(request.getParameter("printCenter_id")));

        printCenterService.update(printCenter);

        return "redirect:/home";

    }
}
