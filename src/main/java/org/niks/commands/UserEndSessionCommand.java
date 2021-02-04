package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;


@AllArgsConstructor
public final class UserEndSessionCommand implements ICommandWithoutUserCheck {

    private final IUserService userService;

    @Override
    public String getName() {
        return "log out";
    }

    @Override
    public String getDescription() {
        return "Log out";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        System.out.println(userService.getCurrentUser().getUserName() + " logged out");
        userService.setCurrentUser(null);
    }
}
