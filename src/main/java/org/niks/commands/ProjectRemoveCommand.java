package org.niks.commands;

import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class ProjectRemoveCommand extends Command {
    private ProjectService projectService;
    private UserService userService;
    public ProjectRemoveCommand(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
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
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter project name to remove");
                projectService.projectRemove(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
