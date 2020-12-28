package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
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
import java.util.List;

public final class TaskCreateCommand extends CommandWithUserCheck {
    private final ITaskService taskService;

    public TaskCreateCommand(IUserService userService, ITaskService taskService) {
        super(userService);
        this.taskService = taskService;
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
    public void execute(@NotNull final BufferedReader reader) {
        final User currentUser = userService.getCurrentUser();
        if (super.inner()) {
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
                final List<Project> projectList = taskService.projectList();
                if (projectList.isEmpty()) {
                    System.out.println("Project list is empty");
                }
                if (!taskName.equals("")) {
                    for (Project project : projectList) {
                        if (project.getProjectName().equals(projectName)) {
                            final Task task = new Task(randomNumber(), taskName, projectName, taskDescription,
                                    dateFormat.parse(startDate), dateFormat.parse(finishDate), currentUser.getUserID(),
                                    Status.PLANNED, new Date());
                            taskService.create(task);
                            System.out.println("Task " + task.getTaskName() + " created and added to the project "
                                    + task.getProjectName());
                        } else {
                            System.out.println("No such existing project");
                        }
                    }
                }
            } catch (IOException | ParseException e) {
                System.out.println("Incorrect date");
            }
        }
    }

    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
