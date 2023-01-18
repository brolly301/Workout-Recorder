package WorkoutApplication.MySQL;

import WorkoutApplication.Pages.Exercises;

import java.sql.*;


public class InsertIntoWorkoutLogsTable {

    //Inserts the Set, Rep & Weight amounts then generates the workout and exerciseID using its below methods
    public void insertIntoWorkoutLogs(String field_setNumber, String field_repNumber, String field_weightAmount) {
        try {
            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO `workout`.`workoutlogs`" + " (`SetNumber`, `RepNumber` , `WeightAmount`, `WorkoutID`, `ExerciseID`)"
                    + "VALUES('" + field_setNumber + "','" + field_repNumber + "','" + field_weightAmount + "','" + workoutID() + "','" + exerciseID() + "')";
            statement.executeUpdate(insert);

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Result set which finds out the most recently entered workoutID from the workouts table
    public int workoutID() throws SQLException {

        int workoutID = 0;

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT workoutID FROM workouts";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            workoutID = rs.getInt("WorkoutID");
        }
        return workoutID;
    }

    //Result set which finds out the most recently entered exerciseID from the workoutExercise table
    public int exerciseID() throws SQLException {

        int exerciseID = 0;

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT exerciseID FROM workoutExercise";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            exerciseID = rs.getInt("ExerciseID");
        }

        return exerciseID;
    }

    //Result set which finds out the exerciseID from the exercise table using the exerciseName
    public int exerciseName(String exerciseName) throws SQLException {

        int exerciseID = 0;

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT exerciseID FROM exercises WHERE exerciseName ='" + exerciseName + "'";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            exerciseID = rs.getInt("ExerciseID");
        }

        return exerciseID;
    }


}


