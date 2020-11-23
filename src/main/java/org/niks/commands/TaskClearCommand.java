package org.niks.commands;

import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

public class TaskClearCommand extends Command {
    private TaskService taskService;
    private UserService userService;

    public TaskClearCommand(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "task-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all tasks";
    }

    @Override
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            taskService.taskClear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
