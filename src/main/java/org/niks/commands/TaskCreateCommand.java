package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Value
public class TaskCreateCommand extends Command {
    TaskService taskService;
    UserService userService;

    @NotNull
    public TaskCreateCommand(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a task";
    }

    @Override
    public void execute(BufferedReader reader) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                System.out.println("Enter project to include to");
                @NotNull
                String projectName = reader.readLine();
                System.out.println("Enter task name");
                @NotNull
                String taskName = reader.readLine();
                System.out.println("Enter task description");
                @NotNull
                String taskDescription = reader.readLine();
                System.out.println("Enter starting date dd.MM.yyyy");
                @NotNull
                String startDate = reader.readLine();
                System.out.println("Enter finishing date dd.MM.yyyy");
                @NotNull
                String finishDate = reader.readLine();
                Task task = new Task(randomNumber(), taskName, projectName, taskDescription, dateFormat.parse(startDate),
                        dateFormat.parse(finishDate), currentUser.getUserID());
                taskService.create(task);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }

    public static long randomNumber() {
        @NotNull
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
