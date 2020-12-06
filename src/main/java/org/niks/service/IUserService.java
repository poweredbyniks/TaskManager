package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

public interface IUserService <T> {

    User getCurrentUser();

    void setCurrentUser(@Nullable final T currentUser);

    void create(@NotNull final String userName, @NotNull final String password);

    T userVerify(@NotNull final String userName, @NotNull final String password);

    void userInfo(@NotNull final String userName);

    void userNameEdit(@NotNull final String newUserName);

    void passwordEdit(@NotNull final String newPassword);

    long randomNumber();
}
