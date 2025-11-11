package javadatabase;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/kuliah";
    private static final String USER = "afrizal";
    private static final String PASSWORD = "admin123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }

}
