package model;

public class Appointment {
    private String bookingNumber;
    private User user;
    private String date;
    private String ailment;
    private String hospital;

    public Appointment(String bookingNumber, User user, String date, String ailment, String hospital) {
        this.bookingNumber = bookingNumber;
        this.user = user;
        this.date = date;
        this.ailment = ailment;
        this.hospital = hospital;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getAilment() {
        return ailment;
    }

    public String getHospital() {
        return hospital;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
