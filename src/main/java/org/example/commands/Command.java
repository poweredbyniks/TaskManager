package org.example.commands;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public abstract class Command {


    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute(BufferedReader reader) throws IOException;
}

