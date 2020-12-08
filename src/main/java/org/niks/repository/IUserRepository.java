package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    @NotNull List<User> findAll(@NotNull final List<String> names);

    @NotNull Optional<User> findOne(@NotNull String name);

    boolean save(@NotNull User user);

    boolean userNameUpdate(@NotNull final String newUserName, @NotNull final User user);

    boolean passwordUpdate(@NotNull final String password, @NotNull final User user);

    void remove(@NotNull final String name);

    void removeAll();
}
