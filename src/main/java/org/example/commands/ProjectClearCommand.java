package org.example.commands;

import org.example.service.ProjectService;

public class ProjectClearCommand extends Command {
    private ProjectService projectService;

    @Override
    public String getName() {
        return "project-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all projects";
    }

    @Override
    public void execute() {
        projectService.projectClear();
    }
}
