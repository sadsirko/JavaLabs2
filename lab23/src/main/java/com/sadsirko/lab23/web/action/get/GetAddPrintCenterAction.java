package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.model.entity.*;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.SubscriptionService;
import com.sadsirko.lab23.model.service.ThemeService;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.SubscriptionServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ThemeServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetEditAction implements Action {
    private final ThemeService themeService = new ThemeServiceImpl();
    private final PrintCenterService printCenterService = new PrintCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        List<Theme> themeList = themeService.findAll();

        PrintCenter printCenter = printCenterService.find (Integer.parseInt(request.getParameter( "printCenter_id")));
        session.setAttribute("printCenter",printCenter);
        session.setAttribute("themeList",themeList);

        return "/admin/edit";
    }
}