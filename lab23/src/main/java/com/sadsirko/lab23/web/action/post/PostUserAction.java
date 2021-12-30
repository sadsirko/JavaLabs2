package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.Subscription;
import com.sadsirko.lab23.model.service.PersonService;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.SubscriptionService;
import com.sadsirko.lab23.model.service.seviceImp.PersonServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.SubscriptionServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostUserAction implements Action {

    private final ReaderService readerService = new ReaderServiceImpl();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        int readerId = Integer.parseInt(request.getParameter("reader_id"));
        try {
            readerService.changeStatus(readerId);
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
            return "redirect:/error";
        }
        return "redirect:/admin/user";
    }
}
