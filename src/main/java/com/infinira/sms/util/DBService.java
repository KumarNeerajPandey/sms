package com.infinira.sms.util;

import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.text.MessageFormat;

public class DBService {

    private static final String PROPERTY_FILE = "dbconfig.properties";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";

    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;
    private static String dbDrvier;

    private static volatile DBService getInstance;

    public static DBService getInstance() {
        if (getInstance == null) {
            synchronized(DBService.class) {
                if (getInstance == null) {
                    getInstance = new DBService();
                }
            }
        }
        return getInstance;
    }

    private DBService() {
        InputStream inputStream = null;
        Properties prop = null;

        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
            if (inputStream == null) {
                throw new RuntimeException(MessageFormat.format(MSG_001, PROPERTY_FILE));
            }
            prop = new Properties();
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(MessageFormat.format(MSG_002, PROPERTY_FILE), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //throw new RuntimeException (" Unable to close the InputStream.", e);
                }
            }
        }

        dbUrl = prop.getProperty(DB_URL);
        validateProperty(dbUrl);
        dbUserName = prop.getProperty(DB_USERNAME);
        validateProperty(dbUserName);
        dbPassword = prop.getProperty(DB_PASSWORD);
        validateProperty(dbPassword);
        dbDrvier = prop.getProperty(DB_DRIVER);
        validateProperty(dbDrvier);
    }

    private static String validateProperty(String property) {
        if (property == null || property.isBlank()) {
            throw new RuntimeException(MessageFormat.format(MSG_003, PROPERTY_FILE));
        }
        return property;
    }

    public Connection getConnection() {
        Connection conn;
        try {
            //class.forName(DB_DRIVER);  // in Modern JDBC, No need to load driver explicitly.
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format(MSG_003, dbUrl, dbUserName));
        }
        return conn;
    }

    public static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable th) {
                // throw new RuntimeException ("Error occurred while closing ResultSet resource.", th);
                // We should not throw an exception again as it may mask the original exception
                // So here we just need to suppress it or log it for debugging purposes.
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (Throwable th) {
                //throw new RuntimeException ("Error occurred while closing PreparedStatement resource.", th);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable th) {
                //throw new RuntimeException ("Error occurred while closing Connection resource.", th);
            }
        }
    }
	
	public static final String MSG_001 = "Property file [{0}] not found.";
    public static final String MSG_002 = "Failed to load dbconfig property file [{0}].";
    public static final String MSG_003 = "Value specified in the [{0}] is Missing or Empty.";
    public static final String MSG_004 = "Failed to establish connection to database with this DbURL [{0}], Username [{1}].";
}