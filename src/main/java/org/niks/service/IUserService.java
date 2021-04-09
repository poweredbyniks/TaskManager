package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

public interface IUserService {

    void create(@NotNull final User user);

    @Nullable
    User userInfo(final Long userID);

    void passwordEdit(@NotNull final String newPassword, @NotNull final Long userID);
}
