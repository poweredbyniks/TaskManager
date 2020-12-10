package org.niks.commands;

import lombok.AllArgsConstructor;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class TaskSearchCommand extends Command {

    private final IUserService userService;
    private final ITaskService taskService;

    @Override
    public String getName() {
        return "task-search";
    }

    @Override
    public String getDescription() {
        return "Find desired task by name or description";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter task to find");
                String name = reader.readLine();
                taskService.taskSearch(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}

