package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class TaskClearCommand extends Command {


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
        if (UserService.getCurrentUser() != null) {
            TaskService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
