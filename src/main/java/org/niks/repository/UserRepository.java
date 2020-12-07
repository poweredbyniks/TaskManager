package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

public final class UserRepository implements IUserRepository <User>{
    private final Map<String, User> userMap = new HashMap<>();

    @NotNull
    public UserRepository() {
        final User admin = new User(AccessRoles.ADMIN, 1, "niks", UserService.hash("123"));
        userMap.put(admin.getUserName(), admin);
    }

    @NotNull
    public <User> List  findAll(@NotNull final List<String> names) {
        @NotNull List<User> userList = new ArrayList<User>(userMap.values());
        return userList;
    }

    @NotNull
    public Optional<User> findOne(@NotNull final String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    public boolean save(@NotNull final User user) {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
            return true;
        } else {
            System.out.println("User already exists");
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