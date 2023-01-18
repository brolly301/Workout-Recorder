package WorkoutApplication.Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame {
    private JButton createWorkoutButton;
    private JButton exitButton;
    private JButton historyButton;
    private JPanel mainPanel;
    final JFrame frame = new JFrame("Workout Recorder");

    public SplashScreen() {

        myGUI();

        //Loads the initial workout page
        createWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == createWorkoutButton) {
                    frame.dispose();
                    new Workout();
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

        //Nothing yet
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Unavailable at the moment.");
            }
        });
    }

    //Creates initial JFrame UI
    public void myGUI() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(380, 520);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}