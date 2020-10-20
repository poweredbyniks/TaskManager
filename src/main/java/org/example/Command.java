package org.example;

public interface Command {
    public String getName();

    public String getDescription();

    public void execute();
}

