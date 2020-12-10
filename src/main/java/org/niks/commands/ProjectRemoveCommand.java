package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class ProjectRemoveCommand extends Command {
    private final IProjectService projectService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "project-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a project";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter project name to remove");
                projectService.remove(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
