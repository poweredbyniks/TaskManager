package org.example.commands;

import org.example.entity.User;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserEditCommand extends Command {
    private UserService userService;

    public UserEditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-edit";
    }

    @Override
    public String getDescription() {
        return "Edit user name";
    }

    @Override
    public void execute(BufferedReader reader, User user) throws IOException {

    }
}
