package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class UserAuthorizationCommand extends Command {
    private final UserService userService;

    @Override
    public String getName() {
        return "user-login";
    }

    @Override
    public String getDescription() {
        return "User authorization";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        System.out.println("Enter user name");
        final String userName = reader.readLine();
        System.out.println("Enter password");
        final String password = reader.readLine();
        final User verifiedUser = userService.userVerify(userName, password);
        userService.setCurrentUser(verifiedUser);
    }

}
