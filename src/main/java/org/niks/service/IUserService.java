package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

public interface IUserService {

    @Nullable
    User getCurrentUser();

    void setCurrentUser(@Nullable final User currentUser);

    void create(@NotNull final User user);

    @Nullable
    User userVerify(@NotNull final String userName, @NotNull final String password);

    @NotNull
    User userInfo();

    void passwordEdit(@NotNull final String newPassword);

}
