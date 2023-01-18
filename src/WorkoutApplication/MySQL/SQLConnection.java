package WorkoutApplication.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    //Global SQL Connection Method
    public static Connection connection() throws SQLException {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("localhost", "root", "password");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
