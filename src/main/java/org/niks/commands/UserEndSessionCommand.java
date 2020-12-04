package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class UserEndSessionCommand extends Command {
    private final UserService userService;

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
