package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;
import org.niks.exception.RepositoryException;
import org.niks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(@NotNull final User user) {
        userRepository.save(user);
    }

    @NotNull
    public User userInfo(final Long userID) {
        return userRepository.findById(userID).orElseThrow(() ->
                new RepositoryException("findByID", "UserRepository"));
    }

    public void passwordEdit(@NotNull final String newPassword, @NotNull final Long userID) {
        userRepository.updatePassword(newPassword, userID);
    }
}

