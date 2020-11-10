package org.example.commands;

import org.example.service.ProjectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectRemoveCommand extends Command {
    private ProjectService projectService;

    public ProjectRemoveCommand(ProjectService projectService) {
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
    public void execute(BufferedReader reader) {
        try {
            System.out.println("Enter project name to remove");
            projectService.projectRemove(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
