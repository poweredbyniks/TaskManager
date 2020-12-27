package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class TaskSearchCommand extends CommandWithUserCheck {

    private final ITaskService taskService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "task-search";
    }

    @Override
    public String getDescription() {
        return "Find desired task by name or description";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (super.inner()) {
            try {
                System.out.println("Enter task to find");
                String name = reader.readLine();
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
}

