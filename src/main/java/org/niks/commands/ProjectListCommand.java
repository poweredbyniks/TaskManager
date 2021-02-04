package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.service.IProjectService;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


public final class ProjectListCommand extends CommandWithUserCheck {

    private final IProjectService projectService;

    private final ITaskService taskService;

    public ProjectListCommand(IUserService userService, IProjectService projectService, ITaskService taskService) {
        super(userService);
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "list-p";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        try {
            System.out.println("Order by:\ncreation date\nstart date\nfinish date\nstatus");
            final String outputOrder = reader.readLine();
            final String order = outputOrder.replace(" ", "_").toUpperCase();
            if (order.equals("")) {
                System.out.println("Ordered by creation date");
                final List<Project> projectList = projectService.list();
                writeList(projectList);
            } else {
                System.out.println("Ordered by " + outputOrder);
                final List<Project> projectList = projectService.list(ProjectSort.valueOf(order));
                writeList(projectList);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Try again and choose order: \ncreation date\nstart date\nfinish date\nstatus");
        }
    }

    private void writeList(@NotNull final List<Project> projectList) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Project project : projectList) {
            System.out.println("\nPROJECT name: "
                    + project.getProjectName()
                    + "\nStatus: " + project.getProjectStatus().getStatus()
                    + "\nDescription: " + project.getProjectDescription()
                    + "\nStart date: " + dateFormat.format(project.getStartDate())
                    + "\nFinish date: " + dateFormat.format(project.getFinishDate()));

            List<Task> list = taskService.list().stream()
                    .filter(task -> task.getProjectID() == project.getProjectID())
                    .collect(Collectors.toList());

            if (list.size() != 0) {
                list.forEach((task -> System.out.println("Tasks:"
                        + "\nTASK name: " + task.getTaskName()
                        + "\nTask status: " + task.getTaskStatus().getStatus()
                        + "\nTask description: " + task.getTaskDescription()
                        + "\nStart date: " + dateFormat.format(task.getStartDate())
                        + "\nFinish date: " + dateFormat.format(task.getFinishDate()))));
            } else {
                System.out.println("Task list is empty");
            }
        }
    }
}
