package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class UserEndSessionCommand extends CommandWithoutUserCheck {
    private final IUserService userService;

    @Override
    public String getName() {
        return "user-exit";
    }

    @Override
    public String getDescription() {
        return "Log out";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        userService.setCurrentUser(null);
    }

}
