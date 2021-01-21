package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ProjectStatusChangeCommand extends CommandWithUserCheck {
    private final IProjectService projectService;

    public ProjectStatusChangeCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @Override
    protected void inner(@NotNull BufferedReader reader) throws IOException {
        System.out.println("Choose a project to change the status");
        String projectName = reader.readLine();
        System.out.println("Choose status: \nplanned\nworking\ndone");
        String newStatus = reader.readLine();
        try {
            if (projectService.findExactMatch(projectName) != null) {
                projectService.create(projectService.findExactMatch(projectName)
                        .withProjectStatus(Status.valueOf(newStatus.toUpperCase())));
                System.out.println("Status of the " + projectName + " is changed to " + newStatus);
            } else {
                System.out.println("Project not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Try again and chose status: \nplanned\nworking\ndone");
        }
    }

    @Override
    public String getName() {
        return "change-status-p";
    }

    @Override
    public String getDescription() {
        return "Change project status";
    }
}
