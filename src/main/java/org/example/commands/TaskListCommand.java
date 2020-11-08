package org.example.commands;

public class TaskListCommand extends Command{
    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void execute() {

    }
}
