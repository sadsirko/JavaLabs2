package com.sadsirko.lab23.model.dao;

import com.sadsirko.lab23.model.DBManager;
import com.sadsirko.lab23.model.exception.DaoException;
import com.sadsirko.lab23.model.entity.PrintCenter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintCenterDAO {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_PRINTCENTER_ID = "id";
    private static final String SQL_PRINTCENTER_NAME = "name";
    private static final String SQL_PRINTCENTER_PRICE = "price";
    private static final String SQL_PRINTCENTER_THEME_ID = "theme_id";

    private static final String SQL_SELECT_PRINTCENTER_BY_ID = "SELECT * FROM print_center WHERE id=?";
    private static final String SQL_SELECT_PRINTCENTER_BY_NAME = "SELECT * FROM print_center WHERE name=?";
    private static final String SQL_SELECT_PRINTCENTER_SORTED_BY_NAME = "SELECT * FROM print_center ORDER BY name";
    private static final String SQL_SELECT_PRINTCENTER_SORTED_BY_MIN = "SELECT * FROM print_center ORDER BY price";
    private static final String SQL_SELECT_PRINTCENTER_SORTED_BY_MAX = "SELECT * FROM print_center ORDER BY price DESC";
    private static final String SQL_UPDATE_PRINTCENTER = "UPDATE print_center SET " +
            "name=?,price =?,theme_id=? WHERE id=?";
    private static final String SQL_DELETE_BY_ID_PRINTCENTER = "delete from print_center where id =?";
    private static final String SQL_INSERT_PRINTCENTER = "INSERT INTO print_center (" +
            "name, price, theme_id) VALUES (?,?,?)";

    public PrintCenter find(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRINTCENTER_BY_ID)) {
                pstmt.setInt(1, id);
                return toPrintCenter(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find printcenter", e);
        }
    }

    public PrintCenter findByName(String printCenterName) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRINTCENTER_BY_NAME)) {
                pstmt.setString(1, printCenterName);
                return toPrintCenter(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find printcenter", e);
        }
    }

    public List<PrintCenter> getSortedByName() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRINTCENTER_SORTED_BY_NAME)) {
                return toPrintCenterList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Problem with sorting", e);
        }
    }

    public List<PrintCenter> getSortedByPriceMin() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRINTCENTER_SORTED_BY_MIN)) {
                return toPrintCenterList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Problem with sorting", e);
        }
    }

    public List<PrintCenter> getSortedByPriceMax() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PRINTCENTER_SORTED_BY_MAX)) {
                return toPrintCenterList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Problem with sorting", e);
        }
    }

    public void save(PrintCenter printCenter) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_PRINTCENTER, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setString(k++, printCenter.getName());
                pstmt.setInt(k++, printCenter.getPrice());
                pstmt.setInt(k++, printCenter.getThemeId());
                int row = pstmt.executeUpdate();
                connection.commit();
                if (row == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        printCenter.setId(id);
                    } else {
                        throw new DaoException("Can't save person");
                    }
                } else {
                    throw new DaoException("Can't save person");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Can't save person", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save person", e);
        }
    }

    public void delete(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_DELETE_BY_ID_PRINTCENTER, Statement.RETURN_GENERATED_KEYS)) {
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

    public void update(PrintCenter printCenter) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_UPDATE_PRINTCENTER, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setString(k++, printCenter.getName());
                pstmt.setInt(k++, printCenter.getPrice());
                pstmt.setInt(k++, printCenter.getThemeId());
                pstmt.setInt(k, printCenter.getId());
                int row = pstmt.executeUpdate();
                connection.commit();
                if (row != 1) {
                    throw new DaoException("Can't update print center");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Can't update print center", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update print center", e);
        }
    }


    private PrintCenter toPrintCenter(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        PrintCenter printCenter = new PrintCenter();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_PRINTCENTER_ID:
                    printCenter.setId(resultSet.getInt(i));
                    break;
                case SQL_PRINTCENTER_NAME:
                    printCenter.setName(resultSet.getString(i));
                    break;
                case SQL_PRINTCENTER_PRICE:
                    printCenter.setPrice(resultSet.getInt(i));
                    break;
                case SQL_PRINTCENTER_THEME_ID:
                    printCenter.setThemeId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return printCenter;
    }

    private List<PrintCenter> toPrintCenterList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<PrintCenter> printCenterList = new ArrayList<>();

        while (true) {
            //resset have cursore
            PrintCenter printCenter = toPrintCenter(metaData, resultSet);
            if (printCenter == null) {
                break;
            }
            printCenterList.add(printCenter);
        }

        return printCenterList;
    }
}
