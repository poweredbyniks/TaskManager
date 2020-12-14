package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@AllArgsConstructor
public final class TaskListCommand extends Command {
    private final ITaskService taskService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (userService.getCurrentUser() != null) {
            System.out.println("Order by creation date\nstart date\nfinish date\nstatus");
            try {
                String order = reader.readLine();
                if (order.equals("")) {
                    System.out.println("Ordered by creation date");
                } else {
                    System.out.println("Ordered by " + order);
                }
                final List<Task> taskList = taskService.list(order);
                for (Task task : taskList) {
                    System.out.println("Task " + task.getTaskName() + " in the project " + task.getProjectName() +
                            "\nStart date: " + dateFormat.format(task.getStartDate()) +
                            "\nFinish date: " + dateFormat.format(task.getFinishDate()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
