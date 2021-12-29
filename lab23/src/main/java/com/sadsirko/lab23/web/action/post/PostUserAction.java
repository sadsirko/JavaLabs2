package com.sadsirko.lab23.web.action.post;

import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Reader;
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

public class PostSubscriptionAction implements Action {

    private final PersonService personService = new PersonServiceImpl();
    private final SubscriptionService subscriptionService = new SubscriptionServiceImpl();
    private final ReaderService readerService = new ReaderServiceImpl();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Subscription sub = new Subscription();
        int printCenterId = Integer.parseInt(request.getParameter("printCenter_id"));
        int readerId = readerService.findByPersonId(Integer.parseInt(request.getParameter("person_id"))).getId();
        int printCenterPrice = Integer.parseInt(request.getParameter("printCenter_price"));
        int bal = readerService.find(readerId).getBalance();
        sub.setPrintCenterId(printCenterId);
        sub.setReaderId(readerId);
        readerService.changeBalance(readerId,bal-printCenterPrice);
        subscriptionService.addSubscripption(sub);
        return "redirect:/reader/cabinet";
    }}
