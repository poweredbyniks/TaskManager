package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;


public class TaskRemoveCommand extends Command {
    private TaskService taskService;

    public TaskRemoveCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "task-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a task";
    }

    @Override
    public void execute(BufferedReader reader, UserService userService) {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter task name to remove");
                taskService.taskRemove(reader.readLine(), userService.getCurrentUser());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
