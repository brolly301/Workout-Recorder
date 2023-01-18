package WorkoutApplication.MySQL;

import WorkoutApplication.Pages.Home;
import WorkoutApplication.Pages.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoLoginLogsTable {

    public int getLastUserID() throws SQLException {

        int userID = 0;
        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT userID from loginlogs";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            userID = rs.getInt("UserID");
        }
        return userID;
    }

    public String getUserName() throws SQLException {

        String userName = null;
        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT userFullName from users WHERE userID = '" + getLastUserID() + "'";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            userName = rs.getString("UserFullName");
        }
        return userName;
    }

}
