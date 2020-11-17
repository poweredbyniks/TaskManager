package org.example.service;

import org.example.AccessRoles;
import org.example.entity.User;
import org.example.repository.UserRepo;
import java.security.SecureRandom;

public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void userCreate(String userName, String password) {
        User user = new User(AccessRoles.USER, randomNumber(), userName, password);
        if (userRepo.save(user)) {
            System.out.println("[User " + userName + " created]");
        }
    }

    public User userSearch(String userName, String password) {
        User consistingUser = userRepo.findOne(userName);
        if (password.equals(consistingUser.getMd5Password())) {
            return consistingUser;
        } else return null;
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
