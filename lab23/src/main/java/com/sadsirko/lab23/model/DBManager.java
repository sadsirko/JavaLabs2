package com.sadsirko.lab23.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {

    public static DataSource getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    private static class DataSourceHolder {
        private static final DataSource INSTANCE;

        //once , when upload
        static {
            DataSource ds = null;
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                //in META-INF/contex.xml
                ds = (DataSource) envContext.lookup("jdbc/SADSIRKO");
            } catch (NamingException e) {
                //some logging
            }
            INSTANCE = ds;
        }
    }

}
