package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserEditCommand extends Command {
    UserService userService;

    @NotNull
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
