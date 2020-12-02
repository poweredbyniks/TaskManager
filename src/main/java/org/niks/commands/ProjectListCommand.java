package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;

@Value
public class ProjectListCommand extends Command {



    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void execute(BufferedReader reader) {
        if (UserService.getCurrentUser() != null) {
            ProjectService.list();
        } else {
            System.out.println("Log in before working");
        }
    }
}
