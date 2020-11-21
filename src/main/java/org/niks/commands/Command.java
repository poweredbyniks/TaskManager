package org.niks.commands;


import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Command {

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute(BufferedReader reader, UserService userService) throws IOException;
}

