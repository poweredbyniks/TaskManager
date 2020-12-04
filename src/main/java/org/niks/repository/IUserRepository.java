package org.niks.repository;

import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

import java.util.Optional;

public interface IUserRepository {
    @Nullable
    Optional findOne(String name);

    boolean save(User user);
}
