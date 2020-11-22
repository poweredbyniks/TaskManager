package org.niks.commands;

import org.niks.service.UserService;

import java.io.BufferedReader;

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
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            userService.userInfo(userService.getCurrentUser().getUserName());
        } else {
            System.out.println("Log in before working");
        }
    }
}
