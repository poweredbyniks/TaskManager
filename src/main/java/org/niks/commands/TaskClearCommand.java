package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class TaskClearCommand extends CommandWithUserCheck {
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
        if (super.inner()) {
            taskService.clear();
            System.out.println("Task list is empty");
        }
    }
}
