package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class TaskRemoveCommand extends Command {
    private final ITaskService taskService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "task-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a task";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (inner()) {
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

    @Override
    public boolean inner() {
        if (userService.getCurrentUser() != null) {
            return true;
        } else {
            System.out.println("Log in before working");
            return false;
        }
    }
}
