package org.niks.repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface IUserRepository<T> {
    @NotNull
    List<T> findAll(@NotNull final List<String> names);

    @NotNull
    Optional<T> findOne(@NotNull String name);

    boolean save(@NotNull T user);

    boolean userNameUpdate(@NotNull final String newUserName, @NotNull final T user);

    boolean passwordUpdate(@NotNull final String password, @NotNull final T user);

    void remove(@NotNull final String name);

    void removeAll();
}
