package ui;

import service.AppointmentService;
import service.UserService;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {
    private UserService userService;
    private AppointmentService appointmentService;

    public MainPanel(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;

        setTitle("Brunel Hospital Appointment Booking System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton bookAppointmentButton = new JButton("Book Appointment");
        bookAppointmentButton.addActionListener(e -> new AppointmentBookingPanel(userService, appointmentService).setVisible(true));
        add(bookAppointmentButton);

        JButton modifyAppointmentButton = new JButton("Modify Appointment");
        modifyAppointmentButton.addActionListener(e -> new ModifyAppointmentPanel(appointmentService).setVisible(true));
        add(modifyAppointmentButton);

        JButton cancelAppointmentButton = new JButton("Cancel Appointment");
        cancelAppointmentButton.addActionListener(e -> new CancelAppointmentPanel(appointmentService).setVisible(true));
        add(cancelAppointmentButton);

        JButton searchHospitalButton = new JButton("Search Hospital");
        searchHospitalButton.addActionListener(e -> new SearchHospitalPanel().setVisible(true));
        add(searchHospitalButton);

        JButton followUpAppointmentButton = new JButton("Follow-Up Appointment");
        followUpAppointmentButton.addActionListener(e -> new FollowUpAppointmentPanel(userService, appointmentService).setVisible(true));
        add(followUpAppointmentButton);
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        AppointmentService appointmentService = new AppointmentService();
        SwingUtilities.invokeLater(() -> new LoginPanel(userService).setVisible(true));
    }
}
