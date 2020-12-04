package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class UserPasswordEditCommand extends Command {
    private final UserService userService;

    @Override
    public String getName() {
        return "password-edit";
    }

    @Override
    public String getDescription() {
        return "Edit current password";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (userService != null) {
            System.out.println("Enter new password");
            final String newPassword = reader.readLine();
            userService.passwordEdit(newPassword);
        } else {
            System.out.println("Log in before working");
        }
    }
}
