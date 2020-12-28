package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

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
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        System.out.println("Order by:\ncreation date\nstart date\nfinish date\nstatus");
        final String outputOrder = reader.readLine();
        final String order = outputOrder.replace(" ", "_").toUpperCase();
        if (order.equals("")) {
            System.out.println("Ordered by creation date");
            final List<Task> taskList = taskService.list();
            listOutput(taskList);
        } else {
            System.out.println("Ordered by " + order);
            final List<Task> taskList = taskService.list(order);
            listOutput(taskList);
        }
    }

    private void listOutput(List<Task> taskList) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : taskList) {
            System.out.println("Task " + task.getTaskName() + " in the project " + task.getProjectName() +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }
}
