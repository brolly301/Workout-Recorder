package WorkoutApplication.Pages;

import WorkoutApplication.MySQL.InsertIntoLoginLogsTable;
import WorkoutApplication.MySQL.InsertIntoUserTable;
import WorkoutApplication.MySQL.SQLConnection;
import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Login extends Home {
    private JPanel loginPanel;
    public JTextField emailField;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton exitButton;

    public Login() {

        myLoginGUI();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    frame.dispose();
                    new MainMenu();
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userEmail = emailField.getText();
                String userPassword = new String(passwordField1.getPassword());
                InsertIntoUserTable userObject = new InsertIntoUserTable();
                InsertIntoLoginLogsTable loginLogsTable = new InsertIntoLoginLogsTable();

                try {
                    if (userObject.userResultSet(userEmail, userPassword).next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful.");

                        insertIntoLoginLogs();
                        frame.dispose();
                        Home home = new Home();
                        home.setLoggedIn();

                    } else {
                        if ((Objects.equals(userEmail, "")) && (Objects.equals(userPassword, ""))) {
                            JOptionPane.showMessageDialog(null, "Email or Password not entered.");
                        } else if (Objects.equals(userEmail, "")) {
                            JOptionPane.showMessageDialog(null, "Email not entered.");
                        } else if (Objects.equals(userPassword, "")) {
                            JOptionPane.showMessageDialog(null, "Password not entered.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Email or Password Invalid. Please check again or register.");
                        }
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public void myLoginGUI() {

        frame.setContentPane(loginPanel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public String getUserEmail() {
        return emailField.getText();
    }

    public void setFrameDispose() {
        frame.dispose();
    }

    public int getUserID() throws SQLException {

        int userID = 0;

        Connection connection = SQLConnection.connection();
        Statement statement = connection.createStatement();

        String select = "SELECT userID FROM users WHERE userEmail ='" + getUserEmail() + "'";
        ResultSet rs = statement.executeQuery(select);

        while (rs.next()) {
            userID = rs.getInt("UserID");
        }
        return userID;
    }

    public void insertIntoLoginLogs() {
        try {

            Connection connection = SQLConnection.connection();
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO `workout`.`loginlogs`" + " (`UserID`)" + "VALUES('" + getUserID() + "')";
            statement.executeUpdate(insert);

            connection.close();

        } catch (Exception f) {
            System.out.println(f);
        }
    }


}


