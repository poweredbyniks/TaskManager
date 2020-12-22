package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;
import org.niks.service.ProjectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


public final class ProjectListCommand extends Command {
    public ProjectListCommand(IProjectService projectService, IUserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

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
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (inner()) {
            System.out.println("Order by:\ncreation date\nstart date\nfinish date\nstatus");
            final String outputOrder = reader.readLine();
            final String order = outputOrder.replace(" ", "_").toUpperCase();
            if (order.equals("")) {
                System.out.println("Ordered by creation date");
                final List<Project> projectList = projectService.list();
                listOutput(projectList);
            } else {
                System.out.println("Ordered by " + outputOrder);
                final List<Project> projectList = projectService.list(ProjectSort.valueOf(order));
                listOutput(projectList);
            }
        }
    }

    @Override
    public boolean inner() {
        if (userService.getCurrentUser() != null) {
            return true;
        } else {
            System.out.println("Log in before working");
            return false;
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
