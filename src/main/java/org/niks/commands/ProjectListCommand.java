package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.ProjectService;

import java.io.BufferedReader;

public class ProjectListCommand extends Command {
    private ProjectService projectService;

    public ProjectListCommand(ProjectService projectService) {
        this.projectService = projectService;
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
    public void execute(BufferedReader reader, User user) {
        if (user != null) {
            projectService.projectList(user);
        } else {
            System.out.println("Log in before working");
        }
    }
}
