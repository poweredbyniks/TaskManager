package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class ProjectListCommand extends Command {
    private final ProjectService projectService;
    private final UserService userService;

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            projectService.list();
        } else {
            System.out.println("Log in before working");
        }
    }
}
