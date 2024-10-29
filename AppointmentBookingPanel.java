package ui;

import model.Appointment;
import model.User;
import service.AppointmentService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AppointmentBookingPanel extends JFrame {
    private JTextField dateField;
    private JTextField ailmentField;
    private JTextField hospitalField;
    private UserService userService;
    private AppointmentService appointmentService;

    public AppointmentBookingPanel(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;

        setTitle("Book Appointment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Ailment:"));
        ailmentField = new JTextField();
        add(ailmentField);

        add(new JLabel("Hospital:"));
        hospitalField = new JTextField();
        add(hospitalField);

        JButton bookAppointmentButton = new JButton("Book Appointment");
        bookAppointmentButton.addActionListener(e -> {
            String date = dateField.getText();
            String ailment = ailmentField.getText();
            String hospital = hospitalField.getText();

            if (date.isEmpty() || ailment.isEmpty() || hospital.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            User user = userService.getCurrentUser();
            String bookingNumber = generateBookingNumber();
            Appointment appointment = new Appointment(bookingNumber, user, date, ailment, hospital);
            appointmentService.bookAppointment(appointment);
            JOptionPane.showMessageDialog(this, "Appointment booked successfully! Booking Number: " + bookingNumber);
        });
        add(bookAppointmentButton);
    }

    private String generateBookingNumber() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        AppointmentService appointmentService = new AppointmentService();
        SwingUtilities.invokeLater(() -> new AppointmentBookingPanel(userService, appointmentService).setVisible(true));
    }
}
