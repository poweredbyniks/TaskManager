package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

import java.util.Optional;

public interface IUserRepository {
    @Nullable
    Optional findOne(@NotNull String name);

    boolean save(@NotNull User user);
}
