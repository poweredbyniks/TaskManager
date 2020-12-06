package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class UserEndSessionCommand extends Command {
    private final IUserService <User> iUserService;

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
        iUserService.setCurrentUser(null);
    }
}
