package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class TaskClearCommand extends Command {
    private final ITaskService taskService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "task-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all tasks";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            taskService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
