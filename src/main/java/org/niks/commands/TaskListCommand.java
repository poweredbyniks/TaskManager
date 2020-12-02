package org.niks.commands;


import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class TaskListCommand extends Command {

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
        if (UserService.getCurrentUser() != null) {
            TaskService.list();
        } else {
            System.out.println("Log in before working");
        }
    }
}
