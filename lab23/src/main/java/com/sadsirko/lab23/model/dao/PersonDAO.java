package com.sadsirko.lab23.model.dao;

import com.sadsirko.lab23.model.DBManager;
import com.sadsirko.lab23.model.exception.DaoException;
import com.sadsirko.lab23.model.entity.Person;

import javax.sql.DataSource;
import java.sql.*;

public class PersonDAO {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_PERSON_ID = "id";
    private static final String SQL_PERSON_EMAIL = "email";
    private static final String SQL_PERSON_ROLE_ID = "role_id";
    private static final String SQL_PERSON_PASSWORD = "password";

    private static final String SQL_SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String SQL_SELECT_PERSON_BY_EMAIL = "SELECT * FROM person WHERE Email=?";
    private static final String SQL_SELECT_PERSON_BY_EMAIL_AND_PASSWORD = "SELECT * FROM person WHERE email=?" +
            " AND password=?";
    private static final String SQL_INSERT_PERSON = "INSERT INTO Person (" +
            "email, password, role_id) VALUES (?,?,?)";

    public Person find(int id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PERSON_BY_ID)) {
                pstmt.setInt(1, id);
                return toPerson(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find person", e);
        }
    }

    public Person findByEmail(String email) {
        try (Connection connection = DATASOURCE.getConnection()) {
            System.out.println("connection got");
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PERSON_BY_EMAIL)) {
                pstmt.setString(1, email);
                return toPerson(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find person", e);
        }
    }

    public Person findByEmailAndPassword(String email, String password) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_PERSON_BY_EMAIL_AND_PASSWORD)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                return toPerson(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find person", e);
        }
    }


    public void save(Person person) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setString(k++, person.getEmail());
                pstmt.setString(k++, person.getPassword());
                pstmt.setInt(k, person.getRoleId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        person.setId(id);
                        connection.commit();
                    } else {
                        throw new DaoException("Can't save person");
                    }
                } else {
                    throw new DaoException("Can't save person");
                }
            }  catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Can't save person", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save person", e);
        }
    }


    private Person toPerson(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Person person = new Person();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_PERSON_ID:
                    person.setId(resultSet.getInt(i));
                    break;
                case SQL_PERSON_PASSWORD:
                    person.setPassword(resultSet.getString(i));
                    break;
                case SQL_PERSON_EMAIL:
                    person.setEmail(resultSet.getString(i));
                    break;
                case SQL_PERSON_ROLE_ID:
                    person.setRoleId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return person;
    }

}
