package org.example.commands;

import org.example.entity.User;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInfoCommand extends Command {
    private UserService userService;

    public UserInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-info";
    }

    @Override
    public String getDescription() {
        return "User info: user ID, user name";
    }

    @Override
    public void execute(BufferedReader reader, User user) throws IOException {
        userService.userInfo(user.getUserName());
    }
}
