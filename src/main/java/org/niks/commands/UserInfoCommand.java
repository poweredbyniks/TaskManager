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
        return "user-info";
    }

    @Override
    public String getDescription() {
        return "User info: user ID, user name";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        User user = userService.userInfo(userService.getCurrentUser().getUserName());
        System.out.println("User ID is: " + user.getUserID()
                + "\nUser name is: " + user.getUserName());
    }
}
