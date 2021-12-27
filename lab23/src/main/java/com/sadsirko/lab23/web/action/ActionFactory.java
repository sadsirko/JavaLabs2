package com.sadsirko.lab23.web.action;

import com.sadsirko.lab23.web.action.get.*;
import com.sadsirko.lab23.web.action.post.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/home", new GetHomeAction());
        actions.put("GET/login", new GetLoginAction());
        actions.put("POST/login", new PostLoginAction());
        actions.put("GET/register", new GetRegisterAction());
        actions.put("POST/register", new PostRegisterAction());
        actions.put("POST/reader/subscribe", new PostSubscriptionAction());
        actions.put("GET/reader/cabinet", new GetCabinetAction());
        actions.put("GET/logout", new GetLogoutAction());
        actions.put("GET/home/price/min", new GetHomePriceActionMin());
        actions.put("GET/home/price/max", new GetHomePriceActionMax());
        actions.put("POST/reader/balance", new PostUpBalanceAction());
        actions.put("POST/admin/deletePrintCenter", new PostDeletePrintCenterAction());
//        actions.put("POST/admin/createPrintCenter", new PostCreatePrintCenterAction());
//        actions.put("POST/admin/editPrintCenter", new PostEditPrintCenterAction());
        actions.put("GET/admin/editPrintCenter", new GetEditAction());

    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}

