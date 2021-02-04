package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.ITaskService;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

public class ProjectTaskSaveCommand extends CommandWithUserCheck {

    private final IProjectService projectService;
    private final ITaskService taskService;

    public ProjectTaskSaveCommand(IUserService userService, IProjectService projectService, ITaskService taskService) {
        super(userService);
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        projectService.serialize();
        taskService.serialize();
        System.out.println("Saved!");
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Save created projects and tasks";
    }
}
