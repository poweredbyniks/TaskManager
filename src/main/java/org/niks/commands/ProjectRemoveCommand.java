package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

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
    public void execute(BufferedReader reader, UserService userService) {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter project name to remove");
                projectService.projectRemove(reader.readLine(), userService.getCurrentUser());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
