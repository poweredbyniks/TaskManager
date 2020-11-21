package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

public class TaskListCommand extends Command {
    private TaskService taskService;

    public TaskListCommand(TaskService taskService) {
        this.taskService = taskService;
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
    public void execute(BufferedReader reader, UserService userService) {
        if (userService.getCurrentUser() != null) {
            taskService.taskList(userService.getCurrentUser());
        } else {
            System.out.println("Log in before working");
        }
    }
}
