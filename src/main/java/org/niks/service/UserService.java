package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.NoSuchElementException;

public class UserService implements IUserService {
    private final UserRepository userRepository;

    private User currentUser;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void create(@NotNull String userName, @NotNull String password) {
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + " logged out");
            setCurrentUser(null);
        }
        if (!userName.equals("")) {
            User user = new User(AccessRoles.USER, randomNumber(), userName, hash(password));
            if (userRepository.save(user)) {
                System.out.println("[User " + userName + " created]");
            } else {
                System.out.println("Something went wrong");
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    @Nullable
    public User userVerify(@NotNull String userName, @NotNull String password) {
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + " logged out");
            setCurrentUser(null);
        }
        try {
            if (hash(password).equals(userRepository.findOne(userName).get().getPasswordHash())) {
                System.out.println("Welcome " + userName);
                return userRepository.findOne(userName).get();
            } else {
                System.out.println("Wrong user name or password");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
        return null;
    }

    public void userInfo(@NotNull String userName) {
        System.out.println("User ID is: " + userRepository.findOne(userName).get().getUserID()
                + "\nUser name is: " + userRepository.findOne(userName).get().getUserName());
    }

    public void userNameEdit(@NotNull String newUserName) {
        if (!newUserName.equals("")) {
            if (userRepository.userNameUpdate(newUserName, currentUser)) {
                System.out.println("Your new user name is " + newUserName);
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    public void passwordEdit(@NotNull String newPassword) {
        if (!newPassword.equals("")) {
            String hashPassword = hash(newPassword);
            if (userRepository.passwordUpdate(hashPassword, currentUser)) {
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
    public static String hash(@NotNull String password) {
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

