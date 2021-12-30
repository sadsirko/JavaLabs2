package com.sadsirko.lab23.model.dao;

import com.sadsirko.lab23.model.DBManager;
import com.sadsirko.lab23.model.exception.DaoException;
import com.sadsirko.lab23.model.entity.Role;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_ROLE_ID = "id";
    private static final String SQL_ROLE_NAME = "name";

    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id=?";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT * FROM role WHERE name=?";
    private static final String SQL_SELECT_ROLE_ALL = "SELECT * FROM role";


    public List<Role> findAll() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ROLE_ALL)) {
                return toRoleList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find role", e);
        }
    }

    public Role find(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID)) {
                pstmt.setInt(1, id);
                return toRole(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find role", e);
        }
    }

    public Role findByName(String name) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ROLE_BY_NAME)) {
                pstmt.setString(1, name);
                return toRole(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find role", e);
        }
    }


    private Role toRole(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Role role = new Role();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_ROLE_ID:
                    role.setId(resultSet.getInt(i));
                    break;
                case SQL_ROLE_NAME:
                    role.setName(resultSet.getString(i));
                    break;
                default:
                    // No operations
            }
        }
        return role;
    }

    private List<Role> toRoleList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Role> roleList = new ArrayList<>();

        while (true) {
            //resset have cursore
            Role role = toRole(metaData, resultSet);
            if (role == null) {
                break;
            }
            roleList.add(role);
        }

        return roleList;
    }
}
