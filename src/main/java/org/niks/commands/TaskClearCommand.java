package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class TaskClearCommand extends Command {
    private final ITaskService iTaskService;
    private final IUserService iUserService;

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
        if (iUserService.getCurrentUser() != null) {
            iTaskService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
