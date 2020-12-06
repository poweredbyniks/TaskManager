package org.niks.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.repository.IUserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public final class UserService implements IUserService <User> {
    private final IUserRepository <User> iUserRepository;

    private User currentUser;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(@Nullable final User currentUser) {
        this.currentUser = currentUser;
    }

    public void create(@NotNull final String userName, @NotNull final String password) {
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + " logged out");
            setCurrentUser(null);
        }
        if (!userName.equals("")) {
            final User user = new User(AccessRoles.USER, randomNumber(), userName, hash(password));
            if (iUserRepository.save(user)) {
                System.out.println("User " + userName + " created");
            } else {
                System.out.println("Something went wrong");
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    @Nullable
    public User userVerify(@NotNull final String userName, @NotNull final String password) {
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + " logged out");
            setCurrentUser(null);
        }
        try {
            if (hash(password).equals(iUserRepository.findOne(userName).get().getPasswordHash())) {
                System.out.println("Welcome " + userName);
                return iUserRepository.findOne(userName).get();
            } else {
                System.out.println("Wrong user name or password");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
        return null;
    }

    public void userInfo(@NotNull final String userName) {
        System.out.println("User ID is: " + iUserRepository.findOne(userName).get().getUserID()
                + "\nUser name is: " + iUserRepository.findOne(userName).get().getUserName());
    }

    public void userNameEdit(@NotNull final String newUserName) {
        if (!newUserName.equals("")) {
            if (iUserRepository.userNameUpdate(newUserName, currentUser)) {
                System.out.println("Your new user name is " + newUserName);
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    public void passwordEdit(@NotNull final String newPassword) {
        if (!newPassword.equals("")) {
            final String hashPassword = hash(newPassword);
            if (iUserRepository.passwordUpdate(hashPassword, currentUser)) {
                System.out.println("Password updated");
            }
        } else {
            System.out.println("Enter valid password and try again");
        }
    }

    public final long randomNumber() {
        final SecureRandom random = new SecureRandom();
        return random.nextInt();
    }

    @NotNull
    public static String hash(@NotNull final String password) {
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

