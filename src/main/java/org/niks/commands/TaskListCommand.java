package org.niks.commands;


import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class TaskListCommand extends Command {
    TaskService taskService;
    UserService userService;

    @NotNull
    public TaskListCommand(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            taskService.list();
        } else {
            System.out.println("Log in before working");
        }
    }
}
