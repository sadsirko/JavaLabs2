package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.model.entity.Theme;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.ThemeService;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ThemeServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAddPrintCenterAction implements Action {
    private final ThemeService themeService = new ThemeServiceImpl();
    private final PrintCenterService printCenterService = new PrintCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<Theme> themeList = themeService.findAll();

            session.setAttribute("themeList", themeList);
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
        }
        return "/admin/add";
    }
}