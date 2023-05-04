package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/csc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection conn = null;
    
    // return the connection
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
        return conn;
    }
    
    // end the connection
    public static void closeConnection() throws SQLException {
          if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}

