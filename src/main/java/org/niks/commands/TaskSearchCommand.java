package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class TaskSearchCommand extends CommandWithUserCheck {
    private final ITaskService taskService;

    public TaskSearchCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "search-t";
    }

    @Override
    public String getDescription() {
        return "Find desired task by name or description";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        try {
            System.out.println("Enter task to find");
            final String name = reader.readLine();
            taskService.taskSearch(name);
            final List<Task> taskList = taskService.taskSearch(name);
            if (taskList.isEmpty()) {
                System.out.println("Task not found");
            }
            for (Task task : taskList) {
                System.out.println(task.getTaskName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

