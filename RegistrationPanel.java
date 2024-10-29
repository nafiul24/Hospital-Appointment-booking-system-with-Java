package ui;

import model.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;

public class RegistrationPanel extends JFrame {
    private JTextField nameField;
    private JTextField dobField;
    private JTextField nextOfKinField;
    private JTextField contactField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserService userService;

    public RegistrationPanel(UserService userService) {
        this.userService = userService;

        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Name label and text field
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        // Date of birth label and text field
        add(new JLabel("Date of Birth:"));
        dobField = new JTextField();
        add(dobField);

        // Next of kin label and text field
        add(new JLabel("Next of Kin:"));
        nextOfKinField = new JTextField();
        add(nextOfKinField);

        // Contact info label and text field
        add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        add(contactField);

        // Username label and text field
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        // Password label and password field
        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String dob = dobField.getText();
            String nextOfKin = nextOfKinField.getText();
            String contact = contactField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || dob.isEmpty() || nextOfKin.isEmpty() || contact.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
            } else {
                User newUser = new User(name, dob, nextOfKin, contact, username, password);
                userService.registerUser(newUser);
                JOptionPane.showMessageDialog(this, "Registration successful!");

                // Redirect to login panel after 2 seconds
                Timer timer = new Timer(2000, evt -> {
                    new LoginPanel(userService).setVisible(true);
                    dispose();
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        add(registerButton);
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        SwingUtilities.invokeLater(() -> new RegistrationPanel(userService).setVisible(true));
    }
}
