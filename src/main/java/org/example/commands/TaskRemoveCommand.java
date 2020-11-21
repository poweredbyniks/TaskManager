package org.example.commands;

import org.example.entity.User;
import org.example.service.TaskService;

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
    public void execute(BufferedReader reader, User user) {
        if (user != null) {
            try {
                System.out.println("Enter task name to remove");
                taskService.taskRemove(reader.readLine(), user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
