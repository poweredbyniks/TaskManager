package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserAuthorizationCommand extends Command {
    private UserService userService;
    private User verifiedUser;

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
    public void execute(BufferedReader reader, User user) throws IOException {
        System.out.println("Enter user name");
        String userName = reader.readLine();
        System.out.println("Enter password");
        String password = reader.readLine();
        verifiedUser = userService.userVerify(userName, password);
        currentUser(verifiedUser);
    }

    public User currentUser(User user) {
        return verifiedUser;
    }
}
