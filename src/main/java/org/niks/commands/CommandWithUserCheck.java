package org.niks.commands;


import org.niks.service.UserService;

public abstract class CommandWithUserCheck implements Command {
    UserService userService;

    public boolean inner() {
        if (userService.getCurrentUser() != null) {
            return true;
        } else {
            System.out.println("Log in before working");
            return false;
        }
    }
}