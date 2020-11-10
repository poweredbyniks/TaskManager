package org.example.commands;

import org.example.service.ProjectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ProjectCreateCommand extends Command {
    private ProjectService projectService;

    public ProjectCreateCommand(ProjectService projectService) {
        this.projectService = projectService;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("[Enter project name]");
        String projectName = reader.readLine();
        System.out.println("[Enter project description]");
        String projectDescription = reader.readLine();
        System.out.println("[Enter starting date dd.MM.yyyy]");
        String startingDate = reader.readLine();
        System.out.println("[Enter finishing date dd.MM.yyyy]");
        String finishingDate = reader.readLine();
        try {
            projectService.projectCreate(projectName, projectDescription, dateFormat.parse(startingDate), dateFormat.parse(finishingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
