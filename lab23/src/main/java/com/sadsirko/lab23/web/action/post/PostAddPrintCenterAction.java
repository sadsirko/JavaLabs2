package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PostAddPrintCenterAction implements Action {
    private final PrintCenterService printCenterService = new PrintCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        PrintCenter printCenter = new PrintCenter();
        printCenter.setName(request.getParameter("name"));
        printCenter.setPrice(Integer.parseInt(request.getParameter("price")));
        printCenter.setThemeId(Integer.parseInt(request.getParameter("themes")));
        try {
            printCenterService.save(printCenter);
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
        return "redirect:/home";

    }
}
