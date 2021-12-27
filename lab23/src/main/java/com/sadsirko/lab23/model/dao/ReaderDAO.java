package com.sadsirko.lab23.model.dao;

import com.sadsirko.lab23.model.DBManager;
import com.sadsirko.lab23.model.exception.DaoException;
import com.sadsirko.lab23.model.entity.Reader;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_READER_ID = "id";
    private static final String SQL_READER_NAME = "name";
    private static final String SQL_READER_BALANCE = "balance";
    private static final String SQL_READER_STATUS = "status";
    private static final String SQL_READER_PERSON_ID = "person_id";

    private static final String SQL_SELECT_READER = "SELECT * FROM reader";
    private static final String SQL_SELECT_READER_BY_ID = "SELECT * FROM reader WHERE id=?";
    private static final String SQL_SELECT_READER_BY_PERSON_ID = "SELECT * FROM reader WHERE person_id=?";


    private static final String SQL_UPDATE_BALANCE_READER_BY_PERSON_ID = "update reader set balance = ? where id = ?";
    private static final String SQL_UPDATE_STATUS_READER_BY_PERSON_ID = "update reader set status = " +
            "not status where id =?";

    private static final String SQL_INSERT_READER = "INSERT INTO reader (" +
            "name, balance, status, person_id) VALUES (?,?,?,?)";


    public List<Reader> getAllReader() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_READER)) {
                return toReaderList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find person", e);
        }
    }

    public Reader findByPersonId(int personId) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_READER_BY_PERSON_ID)) {
                pstmt.setInt(1, personId);
                return toReader(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find rader by personId", e);
        }
    }

    public Reader findById(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_READER_BY_ID)) {
                pstmt.setInt(1,id);
                return toReader(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find readers", e);
        }
    }

    public void changeStatus(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_STATUS_READER_BY_PERSON_ID)) {
                pstmt.setInt(1, id);
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't update reader");
                }
            } catch (SQLException e) {
                throw new DaoException("Can't update reader", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update readers", e);
        }
    }


    public void updateBalance(int id, int bal) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_BALANCE_READER_BY_PERSON_ID)) {
                int k = 1;
                pstmt.setInt(k++, bal);
                pstmt.setInt(k, id);
                int row = pstmt.executeUpdate();
                connection.commit();
                if (row != 1) {
                    throw new DaoException("Can't update balance");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Can't update balance", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update balance", e);
        }
    }


    public void save(Reader reader) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_READER, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setString(k++, reader.getName());
                pstmt.setInt(k++, reader.getBalance());
                pstmt.setBoolean(k++, reader.getStatus());
                pstmt.setInt(k, reader.getPersonId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        reader.setId(id);
                        connection.commit();
                    } else {
                        throw new DaoException("Can't save reader");
                    }
                } else {
                    throw new DaoException("Can't save reader");
                }
            }  catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Can't save reader", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save reader", e);
        }
    }


    private Reader toReader(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Reader reader = new Reader();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_READER_ID:
                    reader.setId(resultSet.getInt(i));
                    break;
                case SQL_READER_NAME:
                    reader.setName(resultSet.getString(i));
                    break;
                case SQL_READER_BALANCE:
                    reader.setBalance(resultSet.getInt(i));
                    break;
                case SQL_READER_STATUS:
                    reader.setStatus(resultSet.getBoolean(i));
                    break;
                case SQL_READER_PERSON_ID:
                    reader.setPersonId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return reader;
    }
    private List<Reader> toReaderList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Reader> readerList = new ArrayList<>();

        while (true) {
            //resset have cursore
            Reader reader = toReader(metaData, resultSet);
            if (reader == null) {
                break;
            }
            readerList.add(reader);
        }

        return readerList;
    }
}
