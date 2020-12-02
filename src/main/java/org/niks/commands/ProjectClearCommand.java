package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class ProjectClearCommand extends Command {

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
        if (UserService.getCurrentUser() != null) {
            ProjectService.clear();
        } else {
            System.out.println("Log in before working");
        }
    }
}
