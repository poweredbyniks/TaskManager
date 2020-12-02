package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class UserInfoCommand extends Command {
    @Override
    public String getName() {
        return "user-info";
    }

    @Override
    public String getDescription() {
        return "User info: user ID, user name";
    }

    @Override
    public void execute(BufferedReader reader) {
        if (UserService.getCurrentUser() != null) {
            UserService.userInfo(UserService.getCurrentUser().getUserName());
        } else {
            System.out.println("Log in before working");
        }
    }
}
