package com.lvbank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    public static Connection connect() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get("/Users/rishi-20345/Desktop/learn/Struct/struts-examples-master/form-processing/src/main/java/com/lvbank/credentials.properties")));

//            String jdbcUrl = properties.getProperty("url");
//            String user = properties.getProperty("username");
//            String password = properties.getProperty("password");

            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
