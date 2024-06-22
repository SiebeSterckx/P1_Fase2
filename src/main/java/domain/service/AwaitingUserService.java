package domain.service;

import domain.model.User;

import java.util.ArrayList;

public interface AwaitingUserService {
    void addUser(User user);

    User findUserWithEmail(String email);

    User findUserWithId(int id);

    ArrayList<User> getAllUsers();

    void removeUser(int user);

    ArrayList<User> getAllDistributors();

    ArrayList<User> getAllProviders();
}
