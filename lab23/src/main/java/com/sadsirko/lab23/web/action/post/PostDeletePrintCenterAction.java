package com.sadsirko.lab23.web.action.post;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDeletePrintCenterAction implements Action {
    private final PrintCenterService printCenterService = new PrintCenterServiceImpl();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        int printCenter_id = Integer.parseInt(request.getParameter("printCenter_id"));
        printCenterService.delete(printCenter_id);
        return "redirect:/home";
    }
}
