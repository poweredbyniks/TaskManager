package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

public final class UserPasswordEditCommand extends CommandWithUserCheck {

    public UserPasswordEditCommand(IUserService userService) {
        super(userService);
    }

    @Override
    public String getName() {
        return "password-edit-u";
    }

    @Override
    public String getDescription() {
        return "Edit current password";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        System.out.println("Enter new password");
        final String newPassword = reader.readLine();
        if (newPassword.length() >= 3) {
            userService.passwordEdit(newPassword);
            System.out.println("Password updated");
        } else {
            System.out.println("Password length must be at least 3 characters");
        }
    }
}
