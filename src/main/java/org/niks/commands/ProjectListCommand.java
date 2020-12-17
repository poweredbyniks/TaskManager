package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@AllArgsConstructor
public final class ProjectListCommand extends Command {
    private final IProjectService projectService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void execute(@NotNull BufferedReader reader) throws IOException {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Order by:\ncreation date\nstart date\nfinish date\nstatus");
                final String order = reader.readLine();
                if (order.equals("") || order.equals("creation date")) {
                    System.out.println("Ordered by creation date");
                    final List<Project> projectList = projectService.list();
                    listOutput(projectList);
                } else if (order.equals("start date") || order.equals("finish date") || order.equals("status")) {
                    System.out.println("Ordered by " + order);
                    final List<Project> projectList = projectService.list(order);
                    listOutput(projectList);
                } else {
                    System.out.println("Wrong order argument, try again");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }

    private void listOutput(List<Project> projectList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Project project : projectList) {
            System.out.println("Project Name: " + project.getProjectName()
                    + "\nDescription: " + project.getProjectDescription()
                    + "\nStart date: " + dateFormat.format(project.getStartDate())
                    + "\nFinish date: " + dateFormat.format(project.getFinishDate()));
            if (project.getTaskList().size() != 0) {
                for (Task task : project.getTaskList()) {
                    System.out.println("Tasks:"
                            + "\nTask name: " + task.getTaskName()
                            + "\nTask description: " + task.getTaskDescription()
                            + "\nStart date: " + dateFormat.format(task.getStartDate())
                            + "\nFinish date: " + dateFormat.format(task.getFinishDate()));
                }
            } else {
                System.out.println("Task list is empty");
            }
        }
    }
}
