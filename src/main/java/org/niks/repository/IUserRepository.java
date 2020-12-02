package org.niks.repository;

import org.niks.entity.User;

import java.util.Optional;

public interface IUserRepository {
    Optional findOne(String name);

    boolean save(User user);

}
