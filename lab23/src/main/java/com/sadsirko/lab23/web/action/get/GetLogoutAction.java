package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetLogoutAction implements Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
