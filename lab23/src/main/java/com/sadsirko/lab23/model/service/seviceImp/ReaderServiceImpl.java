package com.sadsirko.lab23.model.service.seviceImp;

import com.sadsirko.lab23.model.dao.ReaderDAO;
import com.sadsirko.lab23.model.entity.Reader;
import com.sadsirko.lab23.model.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
        ReaderDAO readerDao = new ReaderDAO();
        @Override
        public List<Reader> findAll() {

                return readerDao.getAllReader();
        }

        @Override
        public Reader find(int id) {
                return readerDao.findById(id);
        }

        @Override
        public Reader findByPersonId(int id) {
                return readerDao.findByPersonId(id);
        }

        @Override
        public void addReader(Reader reader) {
                readerDao.save(reader);
        }

        @Override
        public void changeBalance(int id, int change) {
                readerDao.updateBalance(id,change);
        }

        @Override
        public void changeStatus(int id) {
                readerDao.changeStatus(id);
        }
}