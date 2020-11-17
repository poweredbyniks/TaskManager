package org.example.commands;

import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserRegistrationCommand extends Command {
    private UserService userService;

    public UserRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-reg";
    }

    @Override
    public String getDescription() {
        return "Registration of a new user";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {

    }
}
