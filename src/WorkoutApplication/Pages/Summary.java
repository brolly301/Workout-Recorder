package WorkoutApplication.Pages;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Summary extends Workout {
    private JButton homeButton;
    private JButton exitButton;
    private JPanel summaryPanel;
    private JTable workoutData;
    private JButton exportButton;


    public Summary() {

        mySummaryGUI();

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back to the Home Page?", "Home Page", JOptionPane.YES_NO_OPTION);

                if (options == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    Home splash = new Home();
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Application", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (options == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify file save");
                int userSelection = fileChooser.showSaveDialog(fileChooser);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                    try {
                        FileWriter fileWriter = new FileWriter(fileToSave);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        for (int i = 0; i < workoutData.getRowCount(); i++) {
                            for (int j = 0; j < workoutData.getColumnCount(); j++) {
                                bufferedWriter.write(workoutData.getValueAt(i, j).toString());
                            }
                            bufferedWriter.newLine();
                        }
                        JOptionPane.showMessageDialog(null, "SUCCESSFUL", "Success", JOptionPane.INFORMATION_MESSAGE);
                        bufferedWriter.close();
                        fileWriter.close();

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }

                }


            }
        });
    }

    public void mySummaryGUI() {
        //disposes splash screen
        frame.setContentPane(summaryPanel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(400, 600);
    }

    public JTable getWorkoutData() {
        return workoutData;
    }

    public void tablePagination() {

        getWorkoutData().getColumnModel().getColumn(1).setPreferredWidth(50);
        getWorkoutData().getColumnModel().getColumn(2).setPreferredWidth(33);
        getWorkoutData().getColumnModel().getColumn(3).setPreferredWidth(33);
        getWorkoutData().getColumnModel().getColumn(4).setPreferredWidth(33);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        getWorkoutData().getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        getWorkoutData().getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        getWorkoutData().getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        getWorkoutData().getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        getWorkoutData().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
    }

}
