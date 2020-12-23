package org.niks.commands;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

public interface Command {

    String getName();

    String getDescription();

    void execute(@NotNull final BufferedReader reader) throws IOException;
}
