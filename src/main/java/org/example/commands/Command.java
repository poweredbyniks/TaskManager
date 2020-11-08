package org.example.commands;



public abstract class Command {


    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();
}

