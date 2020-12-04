package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class UserEditCommand extends Command {
    private final UserService userService;

    @Override
    public String getName() {
        return "user-edit";
    }

    @Override
    public String getDescription() {
        return "Edit user name";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (userService != null) {
            System.out.println("Enter new user name");
            final String newUserName = reader.readLine();
            userService.userNameEdit(newUserName);
        } else {
            System.out.println("Log in before working");
        }
    }
}
