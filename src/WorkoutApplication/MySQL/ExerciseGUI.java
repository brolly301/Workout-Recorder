package WorkoutApplication.MySQL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ExerciseGUI implements Runnable {

    private final ExerciseModel model;

    public ExerciseGUI() {
        this.model = new ExerciseModel();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Exercise GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createMainPanel(), BorderLayout.CENTER);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private JScrollPane createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        Dimension tablePanelDimension = new Dimension(0, 0);
        List<ExerciseType> exerciseTypes = model.getExerciseTypes();
        for (ExerciseType exerciseType : exerciseTypes) {
            JPanel tablePanel = new JTablePanel(model, exerciseType).getPanel();
            tablePanelDimension = tablePanel.getPreferredSize();
            panel.add(tablePanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(tablePanelDimension.width + 50, tablePanelDimension.height));

        return scrollPane;
    }

    public class JTablePanel {

        private final ExerciseModel model;

        private final ExerciseType exerciseType;

        private final JPanel panel;

        public JTablePanel(ExerciseModel model, ExerciseType exerciseType) {
            this.model = model;
            this.exerciseType = exerciseType;
            this.panel = createJTablePanel();
        }

        private JPanel createJTablePanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 30, 5));

            JLabel exerciseLabel = new JLabel(exerciseType.getExercise());
            titlePanel.add(exerciseLabel);

            JLabel bodyAreaLabel = new JLabel(exerciseType.getBodyArea());
            titlePanel.add(bodyAreaLabel);

            panel.add(titlePanel, BorderLayout.NORTH);

            JTable table = new JTable(createTableModel());

            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);

            return panel;
        }

        private DefaultTableModel createTableModel() {
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Set");
            tableModel.addColumn("Weight");
            tableModel.addColumn("Reps");

            for (Exercise exercise : model.getExercises()) {
                if (exercise.getExercise().equals(exerciseType.getExercise())) {
                    Object[] rowData = new Object[3];
                    rowData[0] = exercise.getSet();
                    rowData[1] = exercise.getWeight();
                    rowData[2] = exercise.getReps();
                    tableModel.addRow(rowData);
                }
            }

            return tableModel;
        }

        public JPanel getPanel() {
            return panel;
        }

    }

    public class ExerciseModel {

        private final List<Exercise> exercises;

        private final List<ExerciseType> exerciseTypes;

        public ExerciseModel() {
            this.exercises = createExercise();
            this.exerciseTypes = createExerciseTypes(exercises);
        }

        private List<Exercise> createExercise() {
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("Chest Press", "Chest", 1, 10, 12));
            exercises.add(new Exercise("Chest Press", "Chest", 2, 10, 12));
            exercises.add(new Exercise("Chest Press", "Chest", 3, 10, 12));
            exercises.add(new Exercise("Chest Press", "Chest", 4, 10, 12));
            exercises.add(new Exercise("Chest Press", "Chest", 5, 10, 12));
            exercises.add(new Exercise("Deadlift", "Back", 1, 10, 70));
            exercises.add(new Exercise("Deadlift", "Back", 2, 10, 70));
            exercises.add(new Exercise("Deadlift", "Back", 3, 10, 70));
            exercises.add(new Exercise("Deadlift", "Back", 4, 10, 70));
            exercises.add(new Exercise("Deadlift", "Back", 5, 10, 70));
            exercises.add(new Exercise("Kickbacks", "Tricept", 1, 10, 12));
            exercises.add(new Exercise("Kickbacks", "Tricept", 2, 10, 12));
            exercises.add(new Exercise("Kickbacks", "Tricept", 3, 10, 12));
            exercises.add(new Exercise("Kickbacks", "Tricept", 4, 10, 12));
            exercises.add(new Exercise("Kickbacks", "Tricept", 5, 10, 12));

            return exercises;
        }

        private List<ExerciseType> createExerciseTypes(List<Exercise> exercises) {
            List<ExerciseType> exerciseTypes = new ArrayList<>();
            String previousExercise = "", previousBodyArea;
            for (Exercise exercise : exercises) {
                if (!previousExercise.equals(exercise.getExercise())) {
                    previousExercise = exercise.getExercise();
                    previousBodyArea = exercise.getBodyArea();
                    exerciseTypes.add(new ExerciseType(previousExercise, previousBodyArea));
                }
            }

            return exerciseTypes;
        }

        public List<Exercise> getExercises() {
            return exercises;
        }

        public List<ExerciseType> getExerciseTypes() {
            return exerciseTypes;
        }

    }

    public class Exercise {

        private final int set, weight, reps;

        private final String exercise, bodyArea;

        public Exercise(String exercise, String bodyArea, int set, int reps, int weight) {
            this.exercise = exercise;
            this.bodyArea = bodyArea;
            this.set = set;
            this.reps = reps;
            this.weight = weight;
        }

        public int getSet() {
            return set;
        }

        public int getWeight() {
            return weight;
        }

        public int getReps() {
            return reps;
        }

        public String getExercise() {
            return exercise;
        }

        public String getBodyArea() {
            return bodyArea;
        }

    }

    public class ExerciseType {

        private final String exercise, bodyArea;

        public ExerciseType(String exercise, String bodyArea) {
            this.exercise = exercise;
            this.bodyArea = bodyArea;
        }

        public String getExercise() {
            return exercise;
        }

        public String getBodyArea() {
            return bodyArea;
        }

    }

}