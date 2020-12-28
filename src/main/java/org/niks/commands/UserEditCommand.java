package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;


public final class UserEditCommand extends CommandWithUserCheck {
    public UserEditCommand(IUserService userService) {
        super(userService);
    }

    @Override
    public String getName() {
        return "user-edit";
    }

    @Override
    public String getDescription() {
        return "Edit user name";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (super.inner()) {
            System.out.println("Enter new user name");
            final String newUserName = reader.readLine();
            if (!newUserName.equals("")) {
                userService.userNameEdit(newUserName);
                System.out.println("Your new user name is " + newUserName);
            } else {
                System.out.println("Enter valid user name and try again");
            }
        }
    }
}
