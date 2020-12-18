package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class UserPasswordEditCommand extends Command {
    private final IUserService userService;

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
        if (inner()) {
            System.out.println("Enter new password");
            final String newPassword = reader.readLine();
            if (!newPassword.equals("")) {
                userService.passwordEdit(newPassword);
                System.out.println("Password updated");
            } else {
                System.out.println("Enter valid password and try again");
            }
        }
    }

    @Override
    public boolean inner() {
        if (userService.getCurrentUser() != null) {
            return true;
        } else {
            System.out.println("Log in before working");
            return false;
        }
    }
}
