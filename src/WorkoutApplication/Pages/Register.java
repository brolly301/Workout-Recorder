package WorkoutApplication.Pages;

import WorkoutApplication.MySQL.InsertIntoUserTable;
import WorkoutApplication.MySQL.SQLConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends Home {
    private JPanel registerPanel;

    private JButton registerButton;
    private JButton exitButton;
    private JTextField dateOfBirthField;
    private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JLabel register;

    private JTextField nameField;

    public Register() {

        myRegisterGUI();
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userEmail = emailField.getText();
                String userPassword = new String(passwordField.getPassword());
                String userName = nameField.getText();
                String userDOB = dateOfBirthField.getText();
                String userNumber = phoneNumberField.getText();
                InsertIntoUserTable userObject = new InsertIntoUserTable();
                Login login = new Login();
                login.setFrameDispose();

                try {
                    if (userObject.userResultSet(userEmail).next()) {
                        JOptionPane.showMessageDialog(null, "Email already exists. Please enter a different email.");
                    } else {
                        if ((Objects.equals(userEmail, "")) || (Objects.equals(userPassword, "") || (Objects.equals(userName, "") || (Objects.equals(userDOB, "") || (Objects.equals(userNumber, "")))))) {
                            JOptionPane.showMessageDialog(null, "Please check if all fields have been entered.");
                        } else if ((!userEmail.contains("@")) || !userEmail.contains(".")) {
                            JOptionPane.showMessageDialog(null, "Incorrect email format.");
                        } else if (!isValidMobileNo(userNumber)) {
                            JOptionPane.showMessageDialog(null, "Incorrect number format. Number must be at least 10 digits.");
                        } else if (isNameValid(userName)) {
                            JOptionPane.showMessageDialog(null, "Incorrect name format. Names cannot have numbers.");
                        } else if (!isPasswordValid(userPassword)) {
                            JOptionPane.showMessageDialog(null, "Incorrect password format. Passwords must contain a minimum eight characters, at least one uppercase letter and a number.");
                        } else {
                            userObject.insertIntoUsers(userEmail, userPassword, userName, userDOB, userNumber);
                            login.insertIntoLoginLogs();
                            JOptionPane.showMessageDialog(null, "Registration successful.");
                            insertIntoLoginLogs();
                            frame.dispose();
                            Home home = new Home();
                            home.setLoggedIn();

                        }
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    frame.dispose();
                    new MainMenu();
                }
            }
        });
    }

    public void myRegisterGUI() {
        frame.setContentPane(registerPanel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public String getUserEmail() {
        return emailField.getText();
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

            String insert = "INSERT INTO `workout`.`loginlogs`" + " (`UserID`)"
                    + "VALUES('" + getUserID() + "')";
            statement.executeUpdate(insert);

            connection.close();

        } catch (Exception f) {
            System.out.println(f);
        }
    }

    public boolean isValidMobileNo(String number) {
        //Sets the pattern to be at least 10 digits
        Pattern pattern = Pattern.compile("^\\d{10,11}$");

        //Checks to see if the input string matches the pattern
        Matcher match = pattern.matcher(number);

        //Returns boolean value
        return (match.find() && match.group().equals(number));
    }

    public static boolean isNameValid(String name) {
        //Sets the pattern to be at least 10 digits
        Pattern pattern = Pattern.compile(".*\\d.*");

        //Checks to see if the input string matches the pattern
        Matcher match = pattern.matcher(name);

        //Returns boolean value
        return (match.find() && match.group().equals(name));
    }

    public static boolean isPasswordValid(String password) {
        //Sets the pattern to be at least 10 digits
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");

        //Checks to see if the input string matches the pattern
        Matcher match = pattern.matcher(password);

        //Returns boolean value
        return (match.find() && match.group().equals(password));
    }

}
