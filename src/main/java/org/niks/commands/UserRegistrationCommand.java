package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserRegistrationCommand extends Command {
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
        String userName = reader.readLine();
        System.out.println("Enter password");
        String password = reader.readLine();
        UserService.create(userName, password);
    }
}
