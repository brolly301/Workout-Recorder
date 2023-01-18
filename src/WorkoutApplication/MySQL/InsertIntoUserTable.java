package WorkoutApplication.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoUserTable {

    //Inserts the exerciseName and bodyPart into the Exercise table
    public void insertIntoUsers(String field_userEmail, String field_userPassword, String field_userFullName, String field_userDateOfBirth, String field_userPhoneNumber) {
        try {

            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO `workout`.`users`" + " (`UserEmail`, `UserPassword`, `UserFullName`, `UserDateOfBirth`, `UserPhoneNumber`)"
                    + "VALUES('" + field_userEmail + "','" + field_userPassword + "', '" + field_userFullName + "', '" + field_userDateOfBirth + "', '" + field_userPhoneNumber + "')";
            statement.executeUpdate(insert);

            connection.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Result set which finds out if the exerciseName has already been entered
    public ResultSet userResultSet(String userEmailText, String userPasswordText) throws ClassNotFoundException, SQLException {
        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT * FROM users WHERE UserEmail ='" + userEmailText + "' AND UserPassword = '" + userPasswordText + "'";

        return statement.executeQuery(select);
    }

    //Result set which finds out if the exerciseName has already been entered
    public ResultSet userResultSet(String userEmailText) throws ClassNotFoundException, SQLException {
        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT * FROM users WHERE UserEmail ='" + userEmailText + "'";

        return statement.executeQuery(select);
    }

    public String userResultSet() throws ClassNotFoundException, SQLException {

        String userFullName = null;

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT userFullName from FROM users";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            userFullName = rs.getString("UserFullName");
        }

        return userFullName;
    }

}



