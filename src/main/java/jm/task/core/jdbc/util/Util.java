package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/dbnewtest";
    private static final String login = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

//    public static Connection getConnection(String url, String login, String password) {
//        try {
//            Connection conn = DriverManager.getConnection(url, login, password);
//            System.out.println("huui");
//            return conn;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}



