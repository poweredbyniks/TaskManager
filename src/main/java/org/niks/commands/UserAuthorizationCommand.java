package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserAuthorizationCommand extends Command {
    UserService userService;

    @NotNull
    public UserAuthorizationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-login";
    }

    @Override
    public String getDescription() {
        return "User authorization";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        System.out.println("Enter user name");
        @NotNull
        String userName = reader.readLine();
        System.out.println("Enter password");
        @NotNull
        String password = reader.readLine();
        User verifiedUser = userService.userVerify(userName, password);
        userService.setCurrentUser(verifiedUser);
    }

}
