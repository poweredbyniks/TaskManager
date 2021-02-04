package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


public final class TaskListCommand extends CommandWithUserCheck {

    private final ITaskService taskService;

    public TaskListCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "list-t";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        try {
            System.out.println("Order by:\ncreation date\nstart date\nfinish date\nstatus");
            final String outputOrder = reader.readLine();
            final String order = outputOrder.replace(" ", "_").toUpperCase();
            if (order.equals("")) {
                System.out.println("Ordered by creation date");
                final List<Task> taskList = taskService.list();
                writeList(taskList);
            } else {
                System.out.println("Ordered by " + order);
                final List<Task> taskList = taskService.list(order);
                writeList(taskList);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Try again and choose order: \ncreation date\nstart date\nfinish date\nstatus");
        }
    }

    private void writeList(@NotNull final List<Task> taskList) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : taskList) {
            System.out.println("\nTASK " + task.getTaskName() + " in the project " + task.getProjectName()
                    + "\nTask status: " + task.getTaskStatus().getStatus()
                    + "\nStart date: " + dateFormat.format(task.getStartDate())
                    + "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }
}
