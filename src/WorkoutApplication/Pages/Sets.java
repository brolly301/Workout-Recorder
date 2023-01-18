package WorkoutApplication.Pages;

import WorkoutApplication.MySQL.InsertIntoSummaryTable;
import WorkoutApplication.MySQL.InsertIntoWorkoutLogsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Sets extends Exercises {
    private JButton homeButton;
    private JButton exitButton;
    private JButton addSetButton;
    private JTextField repText;
    private JPanel setsPanel;
    private JButton clearSets;
    private JButton clearReps;
    private JButton clearWeight;
    private JTextField setsText;
    private JTextField weightText;
    private JButton nextExerciseButton;
    private JButton finishWorkoutButton;
    private JLabel exerciseNameLabel;


    public Sets() {

        mySetsGUI();

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

        //Clears sets text
        clearSets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setsText.setText("");
            }
        });

        //Clears reps text
        clearReps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repText.setText("");
            }
        });

        //Clears weight text
        clearWeight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightText.setText("");
            }
        });

        //Stops workout and generates workout table
        finishWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to finish your workout?",
                        "Exit Application", JOptionPane.YES_NO_OPTION);

                if (options == JOptionPane.YES_OPTION) {
                    InsertIntoSummaryTable summary = new InsertIntoSummaryTable();
                    try {
                        frame.dispose();
                        summary.InsertSetsIntoSummaryTable();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        //Returns to exercise page for adding next exercise
        nextExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to choose the next exercise?",
                        "Next Exercise", JOptionPane.YES_NO_OPTION);

                if (options == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new Exercises();
                }
            }
        });

        //Adds all the set information to the workoutLogsTable
        addSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sets = setsText.getText();
                String reps = repText.getText();
                String weight = weightText.getText();

                InsertIntoWorkoutLogsTable workoutLogsObject = new InsertIntoWorkoutLogsTable();

                if (sets.equals("") && reps.equals("") && weight.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a value for Sets, Reps & Weight.");
                } else if (sets.equals("") && reps.equals("")) {
                    JOptionPane.showMessageDialog(null, "Set or Reps not entered.");
                } else if (reps.equals("") && weight.equals("")) {
                    JOptionPane.showMessageDialog(null, "Rep or Weight not entered.");
                } else if (sets.equals("") && weight.equals("")) {
                    JOptionPane.showMessageDialog(null, "Set or Weight not entered.");
                } else if (sets.equals("")) {
                    JOptionPane.showMessageDialog(null, "Set not entered.");
                } else if (reps.equals("")) {
                    JOptionPane.showMessageDialog(null, "Rep not entered.");
                } else if (weight.equals("")) {
                    JOptionPane.showMessageDialog(null, "Weight not entered.");
                } else {
                    workoutLogsObject.insertIntoWorkoutLogs(sets, reps, weight);
                    JOptionPane.showMessageDialog(null, "Set added.");

                    int setNumber = Integer.valueOf(sets);
                    sets = Integer.toString(++setNumber);
                    setsText.setText(sets);
                    repText.setText("");
                    weightText.setText("");
                }
            }
        });
    }

    //Sets the GUI for the exercise page
    public void mySetsGUI() {
        frame.setContentPane(setsPanel);
        frame.setVisible(true);
        frame.setResizable(false);
        setsText.setBorder(null);
        setsText.setBackground(null);
    }

    public void setExerciseNameLabel(String name) {
        exerciseNameLabel.setText(name);
    }
}
