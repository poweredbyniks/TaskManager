package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserRegistrationCommand extends Command {
    UserService userService;

    @NotNull
    public UserRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-reg";
    }

    @Override
    public String getDescription() {
        return "Registration of a new user";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        System.out.println("Enter user name");
        @NotNull
        String userName = reader.readLine();
        System.out.println("Enter password");
        @NotNull
        String password = reader.readLine();
        userService.create(userName, password);
    }
}
