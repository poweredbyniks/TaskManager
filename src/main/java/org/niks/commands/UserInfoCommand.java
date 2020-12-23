package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class UserInfoCommand extends CommandWithUserCheck {
    private final IUserService userService;

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
        if (super.inner()) {
            User user = userService.userInfo(userService.getCurrentUser().getUserName());
            System.out.println("User ID is: " + user.getUserID()
                    + "\nUser name is: " + user.getUserName());
        }
    }
}
