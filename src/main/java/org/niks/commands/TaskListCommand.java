package org.niks.commands;


import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class TaskListCommand extends Command {
    private final TaskService taskService;
    private final UserService userService;

    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            taskService.list();
        } else {
            System.out.println("Log in before working");
        }
    }
}
