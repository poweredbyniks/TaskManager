package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserPasswordEditCommand extends Command {
    UserService userService;

    @NotNull
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
            @NotNull
            String newPassword = reader.readLine();
            userService.passwordEdit(newPassword);
        } else {
            System.out.println("Log in before working");
        }
    }
}
