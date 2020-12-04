package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class UserInfoCommand extends Command {
    private final UserService userService;

    @Override
    public String getName() {
        return "user-info";
    }

    @Override
    public String getDescription() {
        return "User info: user ID, user name";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            userService.userInfo(userService.getCurrentUser().getUserName());
        } else {
            System.out.println("Log in before working");
        }
    }
}
