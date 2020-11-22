package org.niks.repository;

import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    private Map<String, User> userMap = new HashMap<>();

    public UserRepo() {
        User admin = new User(AccessRoles.ADMIN, 1, "niks", passwordGenerate("123"));
        userMap.put(admin.getUserName(), admin);
    }

    public Map<String, User> showAll() {
        return userMap;
    }

    public List<User> findAll(List<String> names) {
        List<User> userList = new ArrayList<>();
        for (String name : names) {
            if (names.contains(name)) {
                userList.add(userMap.get(name));
            }
        }
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
        if (user.getHashPassword().equals(password)) {
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

    private String passwordGenerate(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(UserService.USER_SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}