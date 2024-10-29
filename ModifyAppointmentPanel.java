package ui;

import model.Appointment;
import service.AppointmentService;

import javax.swing.*;
import java.awt.*;

public class ModifyAppointmentPanel extends JFrame {
    private JTextField bookingNumberField;
    private JTextField dateField;
    private JTextField ailmentField;
    private JTextField hospitalField;
    private AppointmentService appointmentService;

    public ModifyAppointmentPanel(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;

        setTitle("Modify Appointment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Booking number label and text field
        add(new JLabel("Booking Number:"));
        bookingNumberField = new JTextField();
        add(bookingNumberField);

        // Date label and text field
        add(new JLabel("New Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        // Ailment label and text field
        add(new JLabel("New Ailment:"));
        ailmentField = new JTextField();
        add(ailmentField);

        // Hospital label and text field
        add(new JLabel("New Hospital:"));
        hospitalField = new JTextField();
        add(hospitalField);

        // Modify appointment button
        JButton modifyAppointmentButton = new JButton("Modify Appointment");
        modifyAppointmentButton.addActionListener(e -> {
            String bookingNumber = bookingNumberField.getText();
            String date = dateField.getText();
            String ailment = ailmentField.getText();
            String hospital = hospitalField.getText();

            if (bookingNumber.isEmpty() || date.isEmpty() || ailment.isEmpty() || hospital.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            Appointment appointment = appointmentService.getAppointmentByBookingNumber(bookingNumber);
            if (appointment == null) {
                JOptionPane.showMessageDialog(this, "Invalid booking number.");
                return;
            }

            appointment.setDate(date);
            appointment.setAilment(ailment);
            appointment.setHospital(hospital);
            JOptionPane.showMessageDialog(this, "Appointment modified successfully!");
        });
        add(modifyAppointmentButton);
    }

    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        SwingUtilities.invokeLater(() -> new ModifyAppointmentPanel(appointmentService).setVisible(true));
    }
}
