package WorkoutApplication.Pages;

import WorkoutApplication.MySQL.InsertIntoExerciseTable;
import WorkoutApplication.MySQL.InsertIntoWorkoutExerciseTable;

import WorkoutApplication.MySQL.SQLConnection;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Exercises extends Workout {
    private JPanel exercisesPanel;
    private JButton homeButton;
    private JButton exitButton;
    private JButton clear2;
    private JButton clear1;
    private JButton addExerciseButton1;
    private JComboBox<String> exerciseList;
    private JComboBox<String> bodyPartList;


    public Exercises() {

        myExercisesGUI();

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

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back to the Home Page?",
                        "Home Page", JOptionPane.YES_NO_OPTION);

                if (options == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    Home splash = new Home();
                }
            }
        });

        clear2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bodyPartList.setSelectedItem("");
            }
        });

        clear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exerciseList.setSelectedItem("");
            }
        });

        //Adds exercise to the exerciseTable and checks to see if all the information is valid
        //Adds exerciseID and workoutID
        addExerciseButton1.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String bodyPart = (String) bodyPartList.getSelectedItem();
                String exerciseName = (String) exerciseList.getSelectedItem();
                InsertIntoExerciseTable exerciseObject = new InsertIntoExerciseTable();
                InsertIntoWorkoutExerciseTable workoutExerciseObject = new InsertIntoWorkoutExerciseTable();

                try {
                    if (exerciseObject.exerciseResultSet(exerciseName).next()) {
                        workoutExerciseObject.insertIntoWorkoutExercise(exerciseName);
                        JOptionPane.showMessageDialog(null, "Exercise added.");
                        frame.dispose();
                        Sets set = new Sets();
                        set.setExerciseNameLabel(exerciseName);
                    } else {
                        if ((Objects.equals(exerciseName, "")) && (Objects.equals(bodyPart, ""))) {
                            JOptionPane.showMessageDialog(null, "Exercise name or Date not entered.");
                        } else if ((Objects.equals(exerciseName, null)) && (Objects.equals(bodyPart, null))) {
                            JOptionPane.showMessageDialog(null, "Exercise name or Date not entered.");
                        } else if (Objects.equals(exerciseName, "")) {
                            JOptionPane.showMessageDialog(null, "Exercise name not entered.");
                        } else if (Objects.equals(bodyPart, "")) {
                            JOptionPane.showMessageDialog(null, "Body area not entered.");
                        } else {
                            exerciseObject.insertIntoExercises(exerciseName, bodyPart);
                            workoutExerciseObject.insertIntoWorkoutExercise(exerciseName);

                            JOptionPane.showMessageDialog(null, "Exercise added.");

                            frame.dispose();
                            Sets set = new Sets();
                            set.setExerciseNameLabel(exerciseName);

                        }
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Adds all the items to the exerciseName comboBox
        exerciseList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                ArrayList<String> nameList = new ArrayList<>(getComboValues());

                exerciseList.removeAllItems();
                for (String name : nameList) {
                    exerciseList.addItem(name);
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        //Adds all the items to the bodyPart comboBox
        bodyPartList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                bodyPartList.removeAllItems();
                setBodyPartList();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }

    //Sets the GUI for the exercise page
    public void myExercisesGUI() {
        frame.setContentPane(exercisesPanel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //Retrieving data from exerciseTable to store in exerciseList combo box
    public ArrayList<String> getComboValues() {

        ArrayList<String> exerciseNames = new ArrayList<>();

        try {
            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();
            String select = "SELECT ExerciseName FROM exercises ORDER BY ExerciseName ASC";
            ResultSet rs = statement.executeQuery(select);

            while (rs.next()) {
                exerciseNames.add(rs.getString("ExerciseName"));
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return exerciseNames;
    }

    //Adding data onto the bodyPartList combo box
    public void setBodyPartList() {

        bodyPartList.addItem("Arms");
        bodyPartList.addItem("Back");
        bodyPartList.addItem("Core");
        bodyPartList.addItem("Chest");
        bodyPartList.addItem("Legs");
        bodyPartList.addItem("Shoulders");
        bodyPartList.addItem("Other");

    }
}



