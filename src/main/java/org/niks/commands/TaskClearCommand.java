package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.TaskService;

import java.io.BufferedReader;

public class TaskClearCommand extends Command {
    private TaskService taskService;

    public TaskClearCommand(TaskService taskService) {
        this.taskService = taskService;
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
    public void execute(BufferedReader reader, User user) {
        if (user != null) {
            taskService.taskClear(user);
        } else {
            System.out.println("Log in before working");
        }
    }
}
