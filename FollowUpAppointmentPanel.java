package ui;

import model.Appointment;
import model.User;
import service.AppointmentService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class FollowUpAppointmentPanel extends JFrame {
    private JTextField previousBookingNumberField;
    private JTextField dateField;
    private JTextField ailmentField;
    private JTextField hospitalField;
    private AppointmentService appointmentService;
    private UserService userService;

    public FollowUpAppointmentPanel(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;

        setTitle("Follow-Up Appointment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Previous Booking Number:"));
        previousBookingNumberField = new JTextField();
        add(previousBookingNumberField);

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Ailment:"));
        ailmentField = new JTextField();
        add(ailmentField);

        add(new JLabel("Hospital:"));
        hospitalField = new JTextField();
        add(hospitalField);

        JButton bookFollowUpAppointmentButton = new JButton("Book Follow-Up Appointment");
        bookFollowUpAppointmentButton.addActionListener(e -> {
            String previousBookingNumber = previousBookingNumberField.getText();
            String date = dateField.getText();
            String ailment = ailmentField.getText();
            String hospital = hospitalField.getText();

            if (previousBookingNumber.isEmpty() || date.isEmpty() || ailment.isEmpty() || hospital.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            Appointment previousAppointment = appointmentService.getAppointmentByBookingNumber(previousBookingNumber);
            if (previousAppointment == null) {
                JOptionPane.showMessageDialog(this, "Invalid previous booking number.");
                return;
            }

            LocalDate previousDate = LocalDate.parse(previousAppointment.getDate(), DateTimeFormatter.ISO_DATE);
            LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            if (ChronoUnit.MONTHS.between(previousDate, newDate) < 1) {
                JOptionPane.showMessageDialog(this, "Follow-up appointment must be at least 1 month after the previous appointment.");
                return;
            }

            User user = userService.getCurrentUser();
            String bookingNumber = generateBookingNumber();
            Appointment appointment = new Appointment(bookingNumber, user, date, ailment, hospital);
            appointmentService.bookAppointment(appointment);
            JOptionPane.showMessageDialog(this, "Follow-up appointment booked successfully! Booking Number: " + bookingNumber);
        });
        add(bookFollowUpAppointmentButton);
    }

    private String generateBookingNumber() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        AppointmentService appointmentService = new AppointmentService();
        SwingUtilities.invokeLater(() -> new FollowUpAppointmentPanel(userService, appointmentService).setVisible(true));
    }
}
