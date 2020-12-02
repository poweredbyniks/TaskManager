package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

public interface UserRepository {
    Map<String, User> userMap = new HashMap<>();

    public static void UserRepo() {
        User admin = new User(AccessRoles.ADMIN, 1, "niks", UserService.hash("123"));
        userMap.put(admin.getUserName(), admin);
    }

    @NotNull
    public static List<User> findAll(List<String> names) {
        List<User> userList = new ArrayList<>(userMap.values());
        return userList;
    }

    @NotNull
    public static Optional<User> findOne(String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    public static boolean save(User user) {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
            return true;
        } else {
            System.out.println("User already exists");
            return false;
        }
    }

    public static boolean userNameUpdate(String newUserName, User user) {
        User userNameUpdateUser = new User(AccessRoles.USER, user.getUserID(), newUserName, user.getPasswordHash());
        userMap.remove(user.getUserName());
        userMap.put(userNameUpdateUser.getUserName(), userNameUpdateUser);
        return true;
    }

    public static boolean passwordUpdate(String password, User user) {
        User passwordUpdateUser = new User(AccessRoles.USER, user.getUserID(), user.getUserName(), password);
        userMap.remove(user.getUserName());
        userMap.put(passwordUpdateUser.getUserName(), passwordUpdateUser);
        return true;
    }

    public static void remove(String name) {
        userMap.remove(name);
    }

    public static void removeAll() {
        userMap.clear();
    }
}
