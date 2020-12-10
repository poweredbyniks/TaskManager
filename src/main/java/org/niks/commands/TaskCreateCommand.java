package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public final class TaskCreateCommand extends Command {
    private final ITaskService taskService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a task";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        final User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                System.out.println("Enter project to include to");
                final String projectName = reader.readLine();
                System.out.println("Enter task name");
                final String taskName = reader.readLine();
                System.out.println("Enter task description");
                final String taskDescription = reader.readLine();
                System.out.println("Enter starting date dd.MM.yyyy");
                final String startDate = reader.readLine();
                System.out.println("Enter finishing date dd.MM.yyyy");
                final String finishDate = reader.readLine();
                final Task task = new Task(randomNumber(), taskName, projectName, taskDescription, dateFormat.parse(startDate),
                        dateFormat.parse(finishDate), currentUser.getUserID(), Status.PLANNED, new Date());
                taskService.create(task);
            } catch (IOException | ParseException e) {
                System.out.println("Incorrect date");
            }
        } else {
            System.out.println("Log in before working");
        }
    }

    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
