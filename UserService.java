package service;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private User currentUser;

    public UserService() {
        users = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
