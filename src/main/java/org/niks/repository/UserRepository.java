package org.niks.repository;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.jetbrains.annotations.NotNull;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.IOException;
import java.util.*;

public final class UserRepository implements IUserRepository, ISerialization<User> {
    private Map<String, User> userMap = new HashMap<>();

    @NotNull
    public UserRepository() throws IOException  {
        final User admin = new User(AccessRoles.ADMIN, 1, "niks", UserService.hash("123"));
        userMap.put(admin.getUserName(), admin);

    }

    @NotNull
    public List<User> findAll(@NotNull final List<String> names) {
        return new ArrayList<>(userMap.values());
    }

    @NotNull
    public Optional<User> findOne(@NotNull final String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    public boolean save(@NotNull final User user) throws IOException {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
            writeJSON(userMap);
            return true;
        } else {
            return false;
        }
    }

    public boolean userNameUpdate(@NotNull final String newUserName, @NotNull final User user) {
        final User userNameUpdateUser = new User(AccessRoles.USER, user.getUserID(), newUserName, user.getPasswordHash());
        userMap.remove(user.getUserName());
        userMap.put(userNameUpdateUser.getUserName(), userNameUpdateUser);
        return true;
    }

    public boolean passwordUpdate(@NotNull final String password, @NotNull final User user) {
        final User passwordUpdateUser = new User(AccessRoles.USER, user.getUserID(), user.getUserName(), password);
        userMap.remove(user.getUserName());
        userMap.put(passwordUpdateUser.getUserName(), passwordUpdateUser);
        return true;
    }

    public void remove(@NotNull final String name) {
        userMap.remove(name);
    }

    public void removeAll() {
        userMap.clear();
    }
}