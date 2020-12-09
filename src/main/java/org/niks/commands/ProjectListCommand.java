package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class ProjectListCommand extends Command {
    private final IProjectService iProjectService;
    private final IUserService iUserService;

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
        if (iUserService.getCurrentUser() != null) {
            iProjectService.list(reader);
        } else {
            System.out.println("Log in before working");
        }
    }
}
