package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {

    private static Connection connection;

    static {
        connect();
    }

    public static void connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/altyn_almas", "root", "2299353a");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}
