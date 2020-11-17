package org.example.commands;

import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserAuthorizationCommand extends Command {
    private UserService userService;

    public UserAuthorizationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-login";
    }

    @Override
    public String getDescription() {
        return "User authorization";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {

    }
}
