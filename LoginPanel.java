package ui;

import service.AppointmentService;
import service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserService userService;

    public LoginPanel(UserService userService) {
        this.userService = userService;

        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            if (userService.authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                SwingUtilities.invokeLater(() -> {
                    new MainPanel(userService, new AppointmentService()).setVisible(true);
                    this.dispose();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
        add(loginButton);
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        SwingUtilities.invokeLater(() -> new LoginPanel(userService).setVisible(true));
    }
}
