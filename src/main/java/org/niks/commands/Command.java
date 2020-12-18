package org.niks.commands;


import java.io.BufferedReader;
import java.io.IOException;

public abstract class Command {

    public abstract String getName();

    public abstract String getDescription();

    public void execute(BufferedReader reader) throws IOException {
        inner();
    }

    public abstract boolean inner();
}

