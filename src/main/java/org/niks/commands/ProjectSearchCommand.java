package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class ProjectSearchCommand extends CommandWithUserCheck {

    private final IProjectService projectService;

    public ProjectSearchCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @Override
    public String getName() {
        return "search-p";
    }

    @Override
    public String getDescription() {
        return "Find desired project by name or description";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        try {
            System.out.println("Enter project to find");
            final String name = reader.readLine();
            projectService.projectSearch(name);
            final List<Project> projectList = projectService.projectSearch(name);
            if (projectList.isEmpty()) {
                System.out.println("Project not found");
            }
            for (Project project : projectList) {
                System.out.println(project.getProjectName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
