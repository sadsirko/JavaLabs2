package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.model.entity.Person;
import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Reader;
import com.sadsirko.lab23.model.entity.Subscription;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.SubscriptionService;
import com.sadsirko.lab23.model.service.seviceImp.PrintCenterServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.SubscriptionServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetEditAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        return "/admin/edit";
    }
}