package org.niks.commands;

import org.niks.service.UserService;

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
    public void execute(BufferedReader reader) throws IOException {
        if (userService != null) {
            System.out.println("Enter new user name");
            String newUserName = reader.readLine();
            userService.userNameEdit(newUserName);
        } else {
            System.out.println("Log in before working");
        }
    }
}
