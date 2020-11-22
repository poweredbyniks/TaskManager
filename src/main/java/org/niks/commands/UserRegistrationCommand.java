package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.UserService;

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
        System.out.println("Enter user name");
        String userName = reader.readLine();
        System.out.println("Enter password");
        String password = reader.readLine();
        userService.userCreate(userName, password);
    }
}
