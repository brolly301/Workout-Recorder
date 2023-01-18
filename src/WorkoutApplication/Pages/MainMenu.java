package WorkoutApplication.Pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Home {

    private JPanel mainMenuPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;

    public MainMenu() {

        myMainMenuGUI();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == loginButton) {
                    frame.dispose();
                    new Login();
                }

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == registerButton) {
                    frame.dispose();
                    new Register();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });
    }


    public void myMainMenuGUI() {
        frame.setContentPane(mainMenuPanel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
