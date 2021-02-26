package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    @NotNull List<User> findAll();

    @NotNull Optional<User> findOne(@NotNull String name);

    void save(@NotNull final User user);

    void passwordUpdate(@NotNull final String password);

    void remove(@NotNull final String name);

}
