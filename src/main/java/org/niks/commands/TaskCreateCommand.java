package org.niks.commands;

import lombok.Value;
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
                System.out.println("[Enter project to include to]");
                String projectName = reader.readLine();
                System.out.println("[Enter task name]");
                String taskName = reader.readLine();
                System.out.println("[Enter task description]");
                String taskDescription = reader.readLine();
                System.out.println("[Enter starting date dd.MM.yyyy]");
                String startDate = reader.readLine();
                System.out.println("[Enter finishing date dd.MM.yyyy]");
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
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
