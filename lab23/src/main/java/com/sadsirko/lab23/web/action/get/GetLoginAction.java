package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        return "/login";
    }
}
