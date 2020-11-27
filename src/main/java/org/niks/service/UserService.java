package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.repository.UserRepo;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.NoSuchElementException;

public class UserService {
    private final UserRepo userRepo;
    private User currentUser;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    @NotNull
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void create(String userName, String password) {
        if (!userName.equals("")) {
        User user = new User(AccessRoles.USER, randomNumber(), userName, hash(password));
            if (userRepo.save(user)) {
                System.out.println("[User " + userName + " created]");
            } else {
                System.out.println("Something went wrong");
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    @Nullable
    public User userVerify(String userName, String password) {
        try {
            if (hash(password).equals(userRepo.findOne(userName).get().getPasswordHash())) {
                System.out.println("Welcome " + userName);
                return userRepo.findOne(userName).get();
            } else {
                System.out.println("Wrong user name or password");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
        return null;
    }

    public void userInfo(String userName) {
        System.out.println("User ID is: " + userRepo.findOne(userName).get().getUserID()
                + "\nUser name is: " + userRepo.findOne(userName).get().getUserName());
    }

    public void userNameEdit(String newUserName) {
        if(!newUserName.equals("")) {
            if (userRepo.userNameUpdate(newUserName, currentUser)) {
                System.out.println("Your new user name is " + newUserName);
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    public void passwordEdit(String newPassword) {
        if(!newPassword.equals("")){
        String hashPassword = hash(newPassword);
        if (userRepo.passwordUpdate(hashPassword, currentUser)) {
            System.out.println("Password updated");
        }
        } else {
            System.out.println("Enter valid password and try again");
        }
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }

    @NotNull
    public static String hash(String password) {
        String hashPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(USER_SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }
}

