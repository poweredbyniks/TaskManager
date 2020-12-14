package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class UserRegistrationCommand extends Command {
    private final IUserService userService;

    @Override
    public String getName() {
        return "user-reg";
    }

    @Override
    public String getDescription() {
        return "Registration of a new user";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (userService.getCurrentUser() != null) {
            System.out.println(userService.getCurrentUser().getUserName() + " logged out");
            userService.setCurrentUser(null);
        }
        System.out.println("Enter user name");
        final String userName = reader.readLine();
        System.out.println("Enter password");
        final String password = reader.readLine();
        if (!userName.equals("")) {
            userService.create(userName, password);
            System.out.println("User " + userName + " created");
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }
}
