package com.sadsirko.lab23.web.action.get;

import com.sadsirko.lab23.model.entity.Reader;
import com.sadsirko.lab23.model.service.ReaderService;
import com.sadsirko.lab23.model.service.seviceImp.ReaderServiceImpl;
import com.sadsirko.lab23.web.action.Action;
import com.sadsirko.lab23.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetUsersAction implements Action {
    private final ReaderService readerService = new ReaderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<Reader> readerList = readerService.findAll();
            request.setAttribute("readerList", readerList);
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
        }

        return "/admin/user";
    }
}
