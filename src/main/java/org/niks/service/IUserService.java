package org.niks.service;

import org.jetbrains.annotations.NotNull;

public interface IUserService {
    void create(@NotNull final String userName, @NotNull final String password);
}
