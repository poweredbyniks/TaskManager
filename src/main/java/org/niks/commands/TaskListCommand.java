package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

public class TaskListCommand extends Command {
    private TaskService taskService;
    private UserService userService;

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
            taskService.taskList(userService.getCurrentUser());
        } else {
            System.out.println("Log in before working");
        }
    }
}
