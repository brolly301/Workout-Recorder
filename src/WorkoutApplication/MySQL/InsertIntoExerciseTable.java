package WorkoutApplication.MySQL;

import WorkoutApplication.Pages.Exercises;

import java.sql.*;

public class InsertIntoExerciseTable {

    //Inserts the exerciseName and bodyPart into the Exercise table
    public void insertIntoExercises(String field_exercise, String field_bodyPart) {
        try {

            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO `workout`.`exercises`" + " (`ExerciseName`, `BodyPart`)" + "VALUES('" + field_exercise + "','" + field_bodyPart + "')";
            statement.executeUpdate(insert);

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Result set which finds out if the exerciseName has already been entered
    public ResultSet exerciseResultSet(String exerciseText) throws ClassNotFoundException, SQLException {
        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT * FROM exercises WHERE ExerciseName ='" + exerciseText + "'";

        return statement.executeQuery(select);
    }

}



