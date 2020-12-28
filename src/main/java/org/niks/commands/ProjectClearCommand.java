package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

public final class ProjectClearCommand extends CommandWithUserCheck {
    IProjectService projectService;

    public ProjectClearCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

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
        if (super.inner()) {
            projectService.clear();
            System.out.println("Project list is plain empty");
        }
    }
}
