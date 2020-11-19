package org.example.commands;

import org.example.entity.User;
import org.example.service.ProjectService;

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
        projectService.projectList(user);
    }
}
