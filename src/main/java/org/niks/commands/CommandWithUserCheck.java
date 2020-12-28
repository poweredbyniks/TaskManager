package org.niks.commands;

import lombok.AllArgsConstructor;
import org.niks.service.IUserService;

@AllArgsConstructor
public abstract class CommandWithUserCheck implements Command {
    IUserService userService;

    public boolean inner() {
        if (userService.getCurrentUser() != null) {
            return true;
        } else {
            System.out.println("Log in before working");
            return false;
        }
    }
}
