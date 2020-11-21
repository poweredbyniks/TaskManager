package org.example.service;

import org.apache.commons.codec.digest.DigestUtils;
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
        User user = new User(AccessRoles.USER, randomNumber(), userName, md5Password(password));
        if (userRepo.save(user)) {
            System.out.println("[User " + userName + " created]");
        } else System.out.println("Something went wrong");
    }

    public User userVerify(String userName, String password) {
        return userRepo.verifyUser(userName, md5Password(password));

    }

    public void userInfo(String userName) {
        System.out.println("User ID is: " + userRepo.findOne(userName).getUserID()
                + "\nUser name is: " + userRepo.findOne(userName).getUserName());
    }

    public void userNameEdit(String newUserName, User user) {
        if (userRepo.userNameUpdate(newUserName, user)) {
            System.out.println("Your new user name is " + newUserName);
        }
    }

    public void passwordEdit(String newPassword, User user) {
        String md5Password = md5Password(newPassword);
        userRepo.passwordUpdate(md5Password, user);
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }

    private String md5Password(String password){
        String md5Password = DigestUtils.md5Hex(password);
        return md5Password;
    }
}
