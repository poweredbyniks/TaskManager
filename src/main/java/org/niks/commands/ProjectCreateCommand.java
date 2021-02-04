package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


public final class ProjectCreateCommand extends CommandWithUserCheck {

    private final IProjectService projectService;

    public ProjectCreateCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @Override
    public String getName() {
        return "create-p";
    }

    @Override
    public String getDescription() {
        return "Creation of a project";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        final User currentUser = userService.getCurrentUser();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("Enter project name");
        final String projectName = reader.readLine();
        System.out.println("Enter project description");
        final String projectDescription = reader.readLine();
        System.out.println("Enter starting date dd.MM.yyyy");
        final String startDate = reader.readLine();
        System.out.println("Enter finishing date dd.MM.yyyy");
        final String finishDate = reader.readLine();
        try {
            if (!projectName.equals("")) {
                final Project project = new Project(
                        currentUser.getUserID(),
                        randomNumber(),
                        projectName,
                        projectDescription,
                        dateFormat.parse(startDate),
                        dateFormat.parse(finishDate),
                        Status.PLANNED,
                        new Date());
                projectService.create(project);
                System.out.println("Project " + project.getProjectName() + " created");
            } else {
                System.out.println("Enter valid project name and try again");
            }
        } catch (ParseException e) {
            System.out.println("Incorrect date");
        }
    }

    public long randomNumber() {
        final SecureRandom random = new SecureRandom();
        return Math.abs(random.nextInt(Integer.MAX_VALUE));
    }
}

