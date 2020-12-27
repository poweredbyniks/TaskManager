package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class ProjectSearchCommand extends CommandWithUserCheck {
    private final IProjectService projectService;
    private final IUserService userService;;

    @Override
    public String getName() {
        return "project-search";
    }

    @Override
    public String getDescription() {
        return "Find desired project by name or description";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (super.inner()) {
            try {
                System.out.println("Enter project to find");
                String name = reader.readLine();
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
}
