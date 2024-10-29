package model;

public class User {
    private String name;
    private String dob;
    private String nextOfKin;
    private String contact;
    private String username;
    private String password;

    public User(String name, String dob, String nextOfKin, String contact, String username, String password) {
        this.name = name;
        this.dob = dob;
        this.nextOfKin = nextOfKin;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public String getContact() {
        return contact;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
