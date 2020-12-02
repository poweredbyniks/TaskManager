package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserEditCommand extends Command {

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
        if (UserService.currentUser != null) {
            System.out.println("Enter new user name");
            String newUserName = reader.readLine();
            UserService.userNameEdit(newUserName);
        } else {
            System.out.println("Log in before working");
        }
    }
}
