package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class ProjectClearCommand extends Command {
    IProjectService iProjectService;
    IUserService <User> iUserService;

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
        if (iUserService.getCurrentUser() != null) {
            iProjectService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
