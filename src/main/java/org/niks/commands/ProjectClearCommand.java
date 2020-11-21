package org.niks.commands;

import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

public class ProjectClearCommand extends Command {
    private ProjectService projectService;

    public ProjectClearCommand(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public String getName() {
        return "project-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all projects";
    }

    @Override
    public void execute(BufferedReader reader, UserService userService) {
        if (userService.getCurrentUser() != null) {
            projectService.projectClear(userService.getCurrentUser());
        } else {
            System.out.println("Log in before working");
        }
    }
}
