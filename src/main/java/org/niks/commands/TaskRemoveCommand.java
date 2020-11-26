package org.niks.commands;

import lombok.Value;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class TaskRemoveCommand extends Command {
    TaskService taskService;
    UserService userService;


    public TaskRemoveCommand(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
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
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter task name to remove");
                taskService.remove(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
