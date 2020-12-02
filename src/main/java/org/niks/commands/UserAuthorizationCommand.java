package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class UserAuthorizationCommand extends Command {

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
        String userName = reader.readLine();
        System.out.println("Enter password");
        String password = reader.readLine();
        User verifiedUser = UserService.userVerify(userName, password);
        UserService.setCurrentUser(verifiedUser);
    }

}
