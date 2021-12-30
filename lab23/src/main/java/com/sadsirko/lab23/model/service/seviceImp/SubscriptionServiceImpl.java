package com.sadsirko.lab23.model.service.seviceImp;

import com.sadsirko.lab23.model.dao.PrintCenterDAO;
import com.sadsirko.lab23.model.dao.SubscriptionDAO;
import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.entity.Subscription;
import com.sadsirko.lab23.model.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionDAO subscriptionDao = new SubscriptionDAO();
    private PrintCenterDAO printCenterDao = new PrintCenterDAO();

    @Override
    public Subscription find(int id) {
        return subscriptionDao.findById(id);
    }

    @Override
    public List<PrintCenter> getNames(int id) {
        List<PrintCenter> printCenterList = new ArrayList<>();
        List<Subscription> subscriptionList = findByReaderId(id);
        for (Subscription sub : subscriptionList) {
            printCenterList.add(printCenterDao.find(sub.getPrintCenterId()));
        }
        return printCenterList;
    }

    @Override
    public List<Subscription> findByReaderId(int id) {
        return subscriptionDao.findByReaderId(id);
    }

    @Override
    public void delete(int printCenterId) {
        subscriptionDao.delete(printCenterId);
    }

    @Override
    public void addSubscripption(Subscription subscription) {
        subscriptionDao.save(subscription);
    }
}
