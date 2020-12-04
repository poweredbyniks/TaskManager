package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class ProjectClearCommand extends Command {
    private final ProjectService projectService;
    private final UserService userService;

    @Override
    public String getName() {
        return "project-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all projects";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            projectService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
