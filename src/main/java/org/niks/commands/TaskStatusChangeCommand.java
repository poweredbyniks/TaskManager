package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Status;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

public class TaskStatusChangeCommand extends CommandWithUserCheck {
    private final ITaskService taskService;

    public TaskStatusChangeCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
    }

    @Override
    protected void inner(@NotNull BufferedReader reader) throws IOException {
        System.out.println("Choose a task to change the status");
        String taskName = reader.readLine();
        System.out.println("Choose status: \nplanned\nworking\ndone");
        String newStatus = reader.readLine();
        try {
            taskService.findExactMatch(taskName).withTaskStatus(Status.valueOf(newStatus.toUpperCase()));
            System.out.println("Status of the " + taskName + " is changed to " + newStatus);
        } catch (IllegalArgumentException e) {
            System.out.println("Try again and chose status: \nplanned\nworking\ndone");
        }
    }

    @Override
    public String getName() {
        return "change-status-t";
    }

    @Override
    public String getDescription() {
        return "Change task status";
    }
}
