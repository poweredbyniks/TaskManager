package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

public final class TaskClearCommand extends CommandWithUserCheck {
    private final ITaskService taskService;

    public TaskClearCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "clear-t";
    }

    @Override
    public String getDescription() {
        return "Removes all tasks";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
            taskService.clear();
            System.out.println("Task list is empty");
    }
}
