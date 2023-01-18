package WorkoutApplication.Pages;

import WorkoutApplication.MySQL.InsertIntoWorkoutTable;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;

public class Workout extends Home {
    private JTextField workoutNameText;
    private JButton exitButton;
    private JButton homeButton;
    private JPanel workoutPanel;
    private JPanel workoutCal;
    private JButton clear2;
    private JButton clear1;
    private JButton addWorkout;
    private JTextField dateText;
    private JPanel datePanel;


    public Workout() {

        myWorkoutGUI();

        //Home button navigation
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back to the Home Page?",
                        "Home Page", JOptionPane.YES_NO_OPTION);

                if (options == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new Home();
                }
            }
        });

        //Exit button navigation
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                        "Exit Application", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (options == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //Clears date text
        clear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateText.setText("");
            }
        });

        //Clears workoutName text
        clear2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutNameText.setText("");
            }
        });

        //Adds workoutName and workoutDate to workoutTable
        addWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String date = dateText.getText();
                String workoutName = workoutNameText.getText();
                InsertIntoWorkoutTable workoutObject = new InsertIntoWorkoutTable();

                try {
                    if (workoutObject.workoutResultSet(workoutName).next()) {
                        JOptionPane.showMessageDialog(null, "Workout Name already added.");
                    } else if ((workoutName.equals("") && date.equals(""))) {
                        JOptionPane.showMessageDialog(null, "Workout Name or Date not entered.");
                    } else if (workoutName.equals("")) {
                        JOptionPane.showMessageDialog(null, "Workout Name not entered.");
                    } else if (date.equals("")) {
                        JOptionPane.showMessageDialog(null, "Date not entered.");
                    } else {
                        workoutObject.insertIntoWorkout(workoutName, date);
                        JOptionPane.showMessageDialog(null, "Workout added.");
                        frame.dispose();
                        new Exercises();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Sets the GUI for the workout page
    public void myWorkoutGUI() {
        //disposes splash screen
        frame.setContentPane(workoutPanel);
        frame.setVisible(true);
    }


}


