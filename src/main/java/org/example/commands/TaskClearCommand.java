package org.example.commands;

public class TaskClearCommand extends Command{
    @Override
    public String getName() {
        return "task-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all tasks";
    }

    @Override
    public void execute() {

    }
}
