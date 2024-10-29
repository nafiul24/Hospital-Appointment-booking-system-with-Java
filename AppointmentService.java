package service;

import model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Appointment> appointments;

    public AppointmentService() {
        appointments = new ArrayList<>();
    }

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Appointment getAppointmentByBookingNumber(String bookingNumber) {
        for (Appointment appointment : appointments) {
            if (appointment.getBookingNumber().equals(bookingNumber)) {
                return appointment;
            }
        }
        return null;
    }

    public void cancelAppointment(String bookingNumber) {
        appointments.removeIf(appointment -> appointment.getBookingNumber().equals(bookingNumber));
    }
}
