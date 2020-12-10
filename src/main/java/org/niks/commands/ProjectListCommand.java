package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class ProjectListCommand extends Command {
    private final IProjectService projectService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void execute(@NotNull BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            projectService.list(reader);
        } else {
            System.out.println("Log in before working");
        }
    }
}
