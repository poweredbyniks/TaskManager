package org.niks.commands;

import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

public class ProjectListCommand extends Command {
    private ProjectService projectService;
    private UserService userService;

    public ProjectListCommand(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            projectService.projectList();
        } else {
            System.out.println("Log in before working");
        }
    }
}
