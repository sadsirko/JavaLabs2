package com.sadsirko.lab23.model.service.seviceImp;

import com.sadsirko.lab23.model.dao.PrintCenterDAO;
import com.sadsirko.lab23.model.entity.PrintCenter;
import com.sadsirko.lab23.model.service.PrintCenterService;
import com.sadsirko.lab23.model.service.SubscriptionService;

import java.util.List;

public class PrintCenterServiceImpl implements PrintCenterService {
    private PrintCenterDAO printCenterDao = new PrintCenterDAO();
    private SubscriptionService subscriptionService = new SubscriptionServiceImpl();
    @Override
    public PrintCenter find(int id) {
        return printCenterDao.find(id);
    }
    public PrintCenter findByName(String name) {
        return printCenterDao.findByName(name);
    }



    @Override
    public List<PrintCenter> findAllSortedName() {
        return printCenterDao.getSortedByName();
    }

    @Override
    public List<PrintCenter> findAllSortedPriceMin() {
        return printCenterDao.getSortedByPriceMin();
    }

    @Override
    public List<PrintCenter> findAllSortedSortedPriceMax() {
        return printCenterDao.getSortedByPriceMax();
    }

    @Override
    public void delete(int id) {
        subscriptionService.delete(id);
        printCenterDao.delete(id);
    }

    @Override
    public void save(PrintCenter printCenter) {
        printCenterDao.save(printCenter);
    }

    @Override
    public void update(PrintCenter printCenter) {
        printCenterDao.update(printCenter);
    }

}
