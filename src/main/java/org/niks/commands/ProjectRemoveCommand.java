package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;


public final class ProjectRemoveCommand extends CommandWithUserCheck {
    private final IProjectService projectService;

    public ProjectRemoveCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @Override
    public String getName() {
        return "project-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a project";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        try {
            System.out.println("Enter project name to remove");
            final String projectToRemove = reader.readLine();
            projectService.remove(projectToRemove);
            System.out.println("Project " + projectToRemove + " removed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
