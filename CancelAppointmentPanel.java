package ui;

import service.AppointmentService;

import javax.swing.*;
import java.awt.*;

public class CancelAppointmentPanel extends JFrame {
    private JTextField bookingNumberField;
    private AppointmentService appointmentService;

    public CancelAppointmentPanel(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;

        setTitle("Cancel Appointment");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Booking Number:"));
        bookingNumberField = new JTextField();
        add(bookingNumberField);

        JButton cancelAppointmentButton = new JButton("Cancel Appointment");
        cancelAppointmentButton.addActionListener(e -> {
            String bookingNumber = bookingNumberField.getText();

            if (bookingNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Booking number must be filled.");
                return;
            }

            if (appointmentService.getAppointmentByBookingNumber(bookingNumber) == null) {
                JOptionPane.showMessageDialog(this, "Invalid booking number.");
                return;
            }

            appointmentService.cancelAppointment(bookingNumber);
            JOptionPane.showMessageDialog(this, "Appointment cancelled successfully!");
        });
        add(cancelAppointmentButton);
    }

    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        SwingUtilities.invokeLater(() -> new CancelAppointmentPanel(appointmentService).setVisible(true));
    }
}
