package com.sadsirko.lab23.model.dao;

import com.sadsirko.lab23.model.DBManager;
import com.sadsirko.lab23.model.exception.DaoException;
import com.sadsirko.lab23.model.entity.Theme;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThemeDAO {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_THEME_ID = "id";
    private static final String SQL_THEME_NAME = "name";

    private static final String SQL_SELECT_THEME_BY_ID = "SELECT * FROM theme WHERE id=?";
    private static final String SQL_SELECT_THEME_BY_NAME = "SELECT * FROM theme WHERE name=?";
    private static final String SQL_SELECT_THEME_ALL = "SELECT * FROM theme";




    public List<Theme> findAll() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_THEME_ALL)) {
                return toThemeList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find theme", e);
        }
    }

    public Theme find(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_THEME_BY_ID)) {
                pstmt.setInt(1, id);
                return toTheme(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find theme", e);
        }
    }

    public Theme findByName(String name) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_THEME_BY_NAME)) {
                pstmt.setString(1, name);
                return toTheme(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find theme", e);
        }
    }



    private Theme toTheme(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Theme theme = new Theme();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_THEME_ID:
                    theme.setId(resultSet.getInt(i));
                    break;
                case SQL_THEME_NAME:
                    theme.setName(resultSet.getString(i));
                    break;
                default:
                    // No operations
            }
        }
        return theme;
    }

    private List<Theme> toThemeList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Theme> themeList = new ArrayList<>();

        while (true) {
            //resset have cursore
            Theme theme = toTheme(metaData, resultSet);
            if (theme == null) {
                break;
            }
            themeList.add(theme);
        }

        return themeList;
    }
}
