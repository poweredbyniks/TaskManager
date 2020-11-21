package org.example.commands;

import org.example.entity.User;
import org.example.service.ProjectService;

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
    public void execute(BufferedReader reader, User user) {
        if (user != null) {
            projectService.projectClear(user);
        } else {
            System.out.println("Log in before working");
        }
    }
}
