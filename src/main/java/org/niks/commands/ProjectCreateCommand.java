package org.niks.commands;

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


public class ProjectCreateCommand extends Command {
    private ProjectService projectService;
    private UserService userService;

    public ProjectCreateCommand(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "project-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a project";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            System.out.println("[Enter project name]");
            String projectName = reader.readLine();
            System.out.println("[Enter project description]");
            String projectDescription = reader.readLine();
            System.out.println("[Enter starting date dd.MM.yyyy]");
            String startDate = reader.readLine();
            System.out.println("[Enter finishing date dd.MM.yyyy]");
            String finishDate = reader.readLine();
            try {
                Project project = new Project(randomNumber(), projectName, projectDescription,
                        dateFormat.parse(startDate), dateFormat.parse(finishDate), new ArrayList<>(), currentUser.getUserID());
                projectService.projectCreate(project);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}

