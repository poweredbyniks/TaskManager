package org.niks.repository;

import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    private Map<String, User> userMap = new HashMap<>();

    public UserRepo() {
        User admin = new User(AccessRoles.ADMIN, 1, "niks", UserService.hash("123"));
        userMap.put(admin.getUserName(), admin);
    }

    public List<User> findAll(List<String> names) {
        List<User> userList = new ArrayList<>(userMap.values());
        return userList;
    }

    public User findOne(String name) {
        return userMap.get(name);
    }

    public boolean save(User user) {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
            return true;
        } else {
            System.out.println("User already exists");
            return false;
        }
    }

    public boolean userNameUpdate(String newUserName, User user) {
        if (userMap.containsValue(user)) {
            userMap.remove(user.getUserName());
            userMap.put(newUserName, user);
        }
        return true;
    }

    public boolean passwordUpdate(String password, User user) {
        if (user.getPasswordHash().equals(password)) {
            userMap.remove(user);
            userMap.put(user.getUserName(), user);
            return true;
        } else {
            return false;
        }
    }

    public void remove(String name) {
        userMap.remove(name);
    }

    public void removeAll() {
        userMap.clear();
    }

}