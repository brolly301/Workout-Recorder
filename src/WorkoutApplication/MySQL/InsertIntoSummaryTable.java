package WorkoutApplication.MySQL;

import WorkoutApplication.Pages.Summary;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class InsertIntoSummaryTable {

    public void InsertSetsIntoSummaryTable() throws SQLException {

        Summary summary = new Summary();
        InsertIntoWorkoutLogsTable workoutLogsObject = new InsertIntoWorkoutLogsTable();

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "select exerciseName, bodyPart, setNumber, repnumber, weightamount from workoutlogs JOIN exercises ON workoutlogs.exerciseID = exercises.exerciseid WHERE workoutID =" + workoutLogsObject.workoutID();
        ResultSet rs = statement.executeQuery(select);
        DefaultTableModel model = (DefaultTableModel) summary.getWorkoutData().getModel();

        String[] columnNames = {"Exercise", "Body Area", "Sets", "Reps", "Weight"};

        model.setColumnIdentifiers(columnNames);

        summary.tablePagination();

        String setNumber = null;
        String repNumber = null;
        String weightAmount = null;
        String exerciseName = null;
        String bodyPart = null;

        while (rs.next()) {
            exerciseName = String.valueOf(rs.getString(1));
            bodyPart = String.valueOf(rs.getString(2));
            setNumber = String.valueOf(rs.getInt(3));
            repNumber = String.valueOf(rs.getInt(4));
            weightAmount = String.valueOf(rs.getInt(5));

            String[] row = {exerciseName, bodyPart, setNumber, repNumber, weightAmount};
            model.addRow(row);
        }
    }
}
