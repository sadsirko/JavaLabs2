package com.sadsirko.lab23.model.dao;

import com.sadsirko.lab23.model.DBManager;
import com.sadsirko.lab23.model.exception.DaoException;
import com.sadsirko.lab23.model.entity.Subscription;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {

    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_SUBSCRIPTION_ID = "id";
    private static final String SQL_SUBSCRIPTION_READER_ID = "reader_id";
    private static final String SQL_SUBSCRIPTION_PRINT_CENTER_ID = "print_center_id";

    private static final String SQL_SELECT_SUBSCRIPTION_BY_ID = "SELECT * FROM subscription WHERE id=?";
    private static final String SQL_SELECT_SUBSCRIPTION_BY_READER_ID = "SELECT * FROM subscription WHERE reader_id=?";
    private static final String SQL_DELETE_BY_PRINT_CENTER_ID = "DELETE FROM subscription WHERE print_center_id=?";

    //
//    private static final String SQL_UPDATE_STATUS_READER_BY_PERSON_ID = "update reader set status = " +
//            "not status where id =?";
    private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscription (" +
            "reader_id, print_center_id) VALUES (?,?)";

    public List<Subscription> findByReaderId(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION_BY_READER_ID)) {
                pstmt.setInt(1, id);
                return toSubscriptionList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find subscription of this reader", e);
        }
    }


    public Subscription findById(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION_BY_ID)) {
                pstmt.setInt(1, id);
                return toSubscription(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find this subscription ", e);
        }
    }

    public void delete(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_DELETE_BY_PRINT_CENTER_ID, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, id);
                int row = pstmt.executeUpdate();
                connection.commit();
                if (row != 1) {
                    throw new DaoException("Can't delete center");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Can't delete print center", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't delete print center", e);
        }
    }


    public void save(Subscription sub) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setInt(k++, sub.getReaderId());
                pstmt.setInt(k, sub.getPrintCenterId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        sub.setId(id);
                        connection.commit();
                    } else {
                        throw new DaoException("Can't save sub");
                    }
                } else {
                    throw new DaoException("Can't save sub");
                }
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save sub", e);
        }
    }


    private Subscription toSubscription(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Subscription sub = new Subscription();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_SUBSCRIPTION_ID:
                    sub.setId(resultSet.getInt(i));
                    break;
                case SQL_SUBSCRIPTION_READER_ID:
                    sub.setReaderId(resultSet.getInt(i));
                    break;
                case SQL_SUBSCRIPTION_PRINT_CENTER_ID:
                    sub.setPrintCenterId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return sub;
    }

    private List<Subscription> toSubscriptionList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Subscription> subscriptionList = new ArrayList<>();

        while (true) {
            //resset have cursore
            Subscription subscription = toSubscription(metaData, resultSet);
            if (subscription == null) {
                break;
            }
            subscriptionList.add(subscription);
        }

        return subscriptionList;
    }
}
