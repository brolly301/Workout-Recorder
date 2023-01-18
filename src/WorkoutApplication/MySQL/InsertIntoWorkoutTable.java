package WorkoutApplication.MySQL;

import java.sql.*;

public class InsertIntoWorkoutTable {

    //Inserts the workoutName and workoutDate into the workout table
    public void insertIntoWorkout(String field_workoutName, String field_workoutDateTime) {
        try {

            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO `workout`.`workouts`" + " (`WorkoutName`, `WorkoutDateTime`)" + "VALUES('" + field_workoutName + "','" + field_workoutDateTime + "')";
            statement.executeUpdate(insert);

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Result set which finds out if the workoutName has already been entered
    public ResultSet workoutResultSet(String workoutText) throws ClassNotFoundException, SQLException {

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT * FROM workouts WHERE WorkoutName ='" + workoutText + "'";
        return statement.executeQuery(select);
    }
}


//select * from workoutlogs JOIN exercises ON workoutlogs.exerciseID = exercises.exerciseid where workoutid = 59;

//This one is with workoutexercise, dont know if top is
//select * from workoutlogs JOIN exercises ON workoutlogs.exerciseID = exercises.exerciseid JOIN workoutexercise ON workoutexercise.exerciseID = workoutlogs.exerciseID
//Add info to table when next exercise is clicked, needs update but