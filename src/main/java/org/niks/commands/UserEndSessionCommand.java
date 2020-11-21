package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserEndSessionCommand extends Command {
    private UserService userService;

    public UserEndSessionCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-exit";
    }

    @Override
    public String getDescription() {
        return "Log out";
    }

    @Override
    public void execute(BufferedReader reader, User user) throws IOException {


    }
}
