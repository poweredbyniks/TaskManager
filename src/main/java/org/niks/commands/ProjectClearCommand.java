package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class ProjectClearCommand extends Command {
    ProjectService projectService;
    UserService userService;

    @NotNull
    public ProjectClearCommand(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
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
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            projectService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
