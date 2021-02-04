package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public abstract class CommandWithUserCheck implements Command {

    protected final IUserService userService;

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (userService.getCurrentUser() != null) {
            inner(reader);
        } else {
            System.out.println("Log in before working");
        }
    }


    protected abstract void inner(@NotNull final BufferedReader reader) throws IOException;
}
