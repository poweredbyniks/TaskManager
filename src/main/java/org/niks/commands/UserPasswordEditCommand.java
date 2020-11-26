package org.niks.commands;

import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserPasswordEditCommand extends Command {
    private UserService userService;

    public UserPasswordEditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "password-edit";
    }

    @Override
    public String getDescription() {
        return "Edit current password";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        if (userService != null) {
            System.out.println("Enter new password");
            String newPassword = reader.readLine();
            userService.passwordEdit(newPassword);
        } else {
            System.out.println("Log in before working");
        }
    }
}
