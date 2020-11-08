package org.example.commands;

public class TaskCreateCommand extends Command{
    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a task";
    }

    @Override
    public void execute() {

    }
}
