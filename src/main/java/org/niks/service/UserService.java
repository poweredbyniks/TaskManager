package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;
import org.niks.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

@Service
public final class UserService implements IUserService {

    private final IUserRepository userRepository;
    private User currentUser;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(@Nullable final User currentUser) {
        this.currentUser = currentUser;
    }

    public void create(@NotNull final User user) {
        userRepository.save(user);
    }

    @Nullable
    public User userVerify(@NotNull final String userName,
                           @NotNull final String password) throws NoSuchElementException {
        User user = null;
        if ((userRepository.findOne(userName).isPresent())) {
            if (hash(password).equals(userRepository.findOne(userName).get().getPasswordHash())) {
                user = userRepository.findOne(userName).get();
            }
        }
        return user;
    }

    @Nullable
    public User userInfo(final long userID) {
        User user = null;
        if (userRepository.findByID(userID).isPresent()) {
            user = userRepository.findByID(userID).get();
        }
        return user;
    }

    public void passwordEdit(final long userID, @NotNull final String newPassword) {
        final String hashPassword = hash(newPassword);
        userRepository.passwordUpdate(hashPassword, currentUser.getUserID());
    }

    @NotNull
    private static String hash(@NotNull final String password) {
        String hashPassword = "";
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(USER_SALT.getBytes(StandardCharsets.UTF_8));
            final byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            final StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }
}

