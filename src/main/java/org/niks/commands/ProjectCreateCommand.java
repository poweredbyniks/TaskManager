package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@AllArgsConstructor
public final class ProjectCreateCommand extends Command {
    private final ProjectService projectService;
    private final UserService userService;

    @Override
    public String getName() {
        return "project-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a project";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        final User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
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
                final Project project = new Project(randomNumber(), projectName, projectDescription,
                        dateFormat.parse(startDate), dateFormat.parse(finishDate), new ArrayList<>(), currentUser.getUserID());
                projectService.create(project);
            } catch (ParseException e) {
                System.out.println("Incorrect date");
            }
        } else {
            System.out.println("Log in before working");
        }
    }

    public long randomNumber() {
        final SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}

