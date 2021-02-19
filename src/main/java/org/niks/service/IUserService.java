package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

import java.io.IOException;

public interface IUserService {

    @Nullable
    User getCurrentUser();

    void setCurrentUser(@Nullable final User currentUser);

    boolean create(@NotNull final User user);

    @Nullable
    User userVerify(@NotNull User user);

    @NotNull
    User userInfo();

    void passwordEdit(@NotNull final String newPassword);

}
