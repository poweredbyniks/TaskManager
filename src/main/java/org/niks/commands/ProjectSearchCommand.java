package org.niks.commands;

import lombok.AllArgsConstructor;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class ProjectSearchCommand extends Command {
    IUserService userService;
    IProjectService projectService;

    @Override
    public String getName() {
        return "project-search";
    }

    @Override
    public String getDescription() {
        return "Find desired project by name or description";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter project to find");
                String name = reader.readLine();
                projectService.projectSearch(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
