package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class UserEndSessionCommand extends Command {

    @Override
    public String getName() {
        return "user-exit";
    }

    @Override
    public String getDescription() {
        return "Log out";
    }

    @Override
    public void execute(BufferedReader reader) {
        UserService.setCurrentUser(null);
    }
}
