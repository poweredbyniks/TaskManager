package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.BufferedReader;

public final class UserInfoCommand extends CommandWithUserCheck {
    public UserInfoCommand(IUserService userService) {
        super(userService);
    }

    @Override
    public String getName() {
        return "info-u";
    }

    @Override
    public String getDescription() {
        return "User info: user ID, user name";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        System.out.println("User ID is: " + userService.userInfo().getUserID()
                + "\nUser name is: " + userService.userInfo().getUserName());
    }
}
