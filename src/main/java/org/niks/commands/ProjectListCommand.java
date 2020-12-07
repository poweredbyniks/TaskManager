package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class ProjectListCommand extends Command {
    private final IProjectService <Project> iProjectService;
    private final IUserService <User> iUserService;

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        if (iUserService.getCurrentUser() != null) {
            iProjectService.list(reader);
        } else {
            System.out.println("Log in before working");
        }
    }
}
