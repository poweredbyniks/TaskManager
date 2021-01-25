package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

public final class TaskRemoveCommand extends CommandWithUserCheck {
    private final ITaskService taskService;

    public TaskRemoveCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "remove-t";
    }

    @Override
    public String getDescription() {
        return "Remove a task";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        try {
            System.out.println("Enter task name to remove");
            final String taskToRemove = reader.readLine();
            taskService.remove(taskToRemove);
            System.out.println("Task " + taskToRemove + "removed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
