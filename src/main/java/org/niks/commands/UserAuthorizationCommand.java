package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class UserAuthorizationCommand implements ICommandWithoutUserCheck {
    private final IUserService userService;
    private final UserEndSessionCommand userEndSessionCommand;

    @Override
    public String getName() {
        return "user-login";
    }

    @Override
    public String getDescription() {
        return "User authorization";
    }

    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (userService.getCurrentUser() != null) {
            userEndSessionCommand.execute(reader);
        }
        System.out.println("Enter user name");
        final String userName = reader.readLine();
        System.out.println("Enter password");
        final String password = reader.readLine();
        try {
            final User verifiedUser = userService.userVerify(userName, password);
            if (verifiedUser != null) {
                System.out.println("Welcome " + userName);
                userService.setCurrentUser(verifiedUser);
            } else {
                System.out.println("Wrong user name or password");
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
    }
}
