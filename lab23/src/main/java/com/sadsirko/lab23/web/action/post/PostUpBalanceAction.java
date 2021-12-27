package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.Subscription;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.SubscriptionService;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.model.service.seviceImp.SubscriptionServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUpBalanceAction implements Action {
    private final ReaderService readerService = new ReaderServiceImpl();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int upBal = Integer.parseInt(request.getParameter("upBal"));
        int readerId = readerService.findByPersonId(Integer.parseInt(request.getParameter("person_id"))).getId();
        int bal = readerService.find(readerId).getBalance();
        readerService.changeBalance(readerId,bal+upBal);
        return "redirect:/reader/cabinet";
    }}