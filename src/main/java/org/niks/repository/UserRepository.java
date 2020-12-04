package org.niks.repository;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

@Value
public class UserRepository implements IUserRepository {
    Map<String, User> userMap = new HashMap<>();

    @NotNull
    public UserRepository() {
        User admin = new User(AccessRoles.ADMIN, 1, "niks", UserService.hash("123"));
        userMap.put(admin.getUserName(), admin);
    }

    @NotNull
    public List<User> findAll(List<String> names) {
        @NotNull List<User> userList = new ArrayList<>(userMap.values());
        return userList;
    }

    @NotNull
    public Optional<User> findOne(@NotNull String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    public boolean save(@NotNull User user) {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
            return true;
        } else {
            System.out.println("User already exists");
            return false;
        }
    }

    public boolean userNameUpdate(@NotNull String newUserName, @NotNull User user) {
        User userNameUpdateUser = new User(AccessRoles.USER, user.getUserID(), newUserName, user.getPasswordHash());
        userMap.remove(user.getUserName());
        userMap.put(userNameUpdateUser.getUserName(), userNameUpdateUser);
        return true;
    }

    public boolean passwordUpdate(@NotNull String password, @NotNull User user) {
        User passwordUpdateUser = new User(AccessRoles.USER, user.getUserID(), user.getUserName(), password);
        userMap.remove(user.getUserName());
        userMap.put(passwordUpdateUser.getUserName(), passwordUpdateUser);
        return true;
    }

    public void remove(@NotNull String name) {
        userMap.remove(name);
    }

    public void removeAll() {
        userMap.clear();
    }

}