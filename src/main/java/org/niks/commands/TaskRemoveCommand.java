package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

public final class TaskRemoveCommand extends CommandWithUserCheck {
    public TaskRemoveCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
    }

    private final ITaskService taskService;

    @Override
    public String getName() {
        return "task-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a task";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        try {
            System.out.println("Enter task name to remove");
            String taskToRemove = reader.readLine();
            taskService.remove(taskToRemove);
            System.out.println("Task " + taskToRemove + "removed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
