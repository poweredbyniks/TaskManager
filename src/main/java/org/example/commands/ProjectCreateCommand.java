package org.example.commands;

import org.example.service.ProjectService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

public class ProjectCreateCommand extends Command {
    private ProjectService projectService;
    @Override
    public String getName() {
        return "project-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a project";
    }

    @Override
    public void execute() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (input != null) {
                System.out.println("[Enter project name]\n[Enter project description]" +
                        "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]");
                projectService.projectCreate(reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
