package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class UserAuthorizationCommand extends Command {
    private final IUserService <User> iUserService;

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
        final User verifiedUser =  iUserService.userVerify(userName, password);
        iUserService.setCurrentUser(verifiedUser);
    }

}
