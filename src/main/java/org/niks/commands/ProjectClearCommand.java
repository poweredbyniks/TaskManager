package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;


public final class ProjectClearCommand extends CommandWithUserCheck {

    private final IProjectService projectService;

    public ProjectClearCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @Override
    public String getName() {
        return "clear-p";
    }

    @Override
    public String getDescription() {
        return "Removes all projects";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) {
        projectService.clear();
        System.out.println("Project list was cleared");
    }
}
