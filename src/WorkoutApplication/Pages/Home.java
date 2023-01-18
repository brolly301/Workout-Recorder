package WorkoutApplication.Pages;

import WorkoutApplication.MySQL.InsertIntoLoginLogsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Home extends JFrame {
    private JButton createWorkoutButton;
    private JButton exitButton;
    private JButton historyButton;
    private JPanel mainPanel;
    private JButton logoutButton;
    private JLabel loggedIn;
    final JFrame frame = new JFrame("Workout Recorder");

    public Home() {

        myGUI();

        //Loads the initial workout page
        createWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == createWorkoutButton) {
                    frame.dispose();
                    Workout workout = new Workout();
                }
            }
        });

        //Exit button navigation
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Application", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                if (options == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (options == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Logged out.");
                    frame.dispose();
                    new MainMenu();
                }
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

    public void setLoggedIn() throws SQLException {
        InsertIntoLoginLogsTable logs = new InsertIntoLoginLogsTable();
        loggedIn.setText(logs.getUserName() + " logged in  ");
    }

}