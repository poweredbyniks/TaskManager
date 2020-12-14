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
public final class UserService implements IUserService {
    private final IUserRepository userRepository;

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
        final User user = new User(AccessRoles.USER, randomNumber(), userName, hash(password));
        userRepository.save(user);
    }

    @Nullable
    public User userVerify(@NotNull final String userName, @NotNull final String password) {
        if (hash(password).equals(userRepository.findOne(userName).get().getPasswordHash())) {
            return userRepository.findOne(userName).get();
        } else {
            return null;
        }
    }

    public User userInfo(@NotNull final String userName) {
        return userRepository.findOne(userName).get();
    }

    public void userNameEdit(@NotNull final String newUserName) {
        userRepository.userNameUpdate(newUserName, currentUser);
    }

    public void passwordEdit(@NotNull final String newPassword) {
        final String hashPassword = hash(newPassword);
        userRepository.passwordUpdate(hashPassword, currentUser);
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

