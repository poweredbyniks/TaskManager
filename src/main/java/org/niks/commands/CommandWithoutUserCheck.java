package org.niks.commands;


import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Command {

    public abstract String getName();

    public abstract String getDescription();

    public void execute(@NotNull final BufferedReader reader) throws IOException {
        inner();
    }

    public abstract boolean inner();
}

