package org.example.repository;

import org.example.entity.Project;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    private static Map<String, User> userMap = new HashMap<>();

    public Map<String, User> showAll() {
        return userMap;
    }

    public List<User> findAll(List<String> names) {
        List<User> projectList = new ArrayList<>();
        for (String name : names) {
            if (names.contains(name))
                projectList.add(userMap.get(name));
        }
        return projectList;
    }

    public User findOne(String name) {
        return userMap.get(name);
    }

    public boolean save(User user) {
        userMap.put(user.getUserName(), user);
        return true;
    }

    public boolean update(Project project) {
        return false;
    }

    public void remove(String name) {
        userMap.remove(name);
    }

    public void removeAll() {
        userMap.clear();
    }
}