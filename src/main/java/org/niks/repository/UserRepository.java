package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.niks.AccessRoles;
import org.niks.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class UserRepository extends Serialization<User> implements IUserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    @NotNull
    public UserRepository() {
        try {
            userMap.putAll(readJSON().stream().collect(Collectors.toMap(User::getUserName, user -> user)));
        } catch (IOException e) {
            System.out.println("No user data found");
        }
    }

    @NotNull
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @NotNull
    public Optional<User> findOne(@NotNull final String name) throws NoSuchElementException {
        return Optional.ofNullable(userMap.get(name));
    }

    public boolean save(@NotNull final User user) throws IOException {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
            writeJSON(userMap, FilePath.USER_FILE_PATH);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> readJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(FilePath.USER_FILE_PATH), User[].class));
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