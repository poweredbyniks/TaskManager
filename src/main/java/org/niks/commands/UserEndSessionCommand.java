package org.niks.commands;

import lombok.Value;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class UserEndSessionCommand extends Command {
    private UserService userService;

    public UserEndSessionCommand(UserService userService) {
        this.userService = userService;
    }

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
        userService.setCurrentUser(null);
    }
}
