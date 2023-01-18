package WorkoutApplication.MySQL;

import java.sql.Connection;
import java.sql.Statement;


public class InsertIntoWorkoutExerciseTable {

    //Inserts the workoutID and exerciseID into the WorkoutExercise table
    //Data from both is generated through methods in the InsertIntoWorkoutLogsTable
    public void insertIntoWorkoutExercise(String exerciseName) {
        try {

            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();

            InsertIntoWorkoutLogsTable logsObject = new InsertIntoWorkoutLogsTable();

            String insert = "INSERT INTO `workout`.`workoutexercise`" + " (`WorkoutID`, `ExerciseID`)" + "VALUES('" + logsObject.workoutID() + "','" + logsObject.exerciseName(exerciseName) + "')";
            statement.executeUpdate(insert);

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
