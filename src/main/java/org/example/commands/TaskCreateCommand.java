package org.example.commands;

import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;
import org.example.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskCreateCommand extends Command {
    private TaskService taskService;

    public TaskCreateCommand(TaskService taskService, ProjectRepo projectRepo) {
        this.taskService = taskService;
        this.projectRepo = projectRepo;
    }

    private ProjectRepo projectRepo;


    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a task";
    }

    @Override
    public void execute() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("[Enter project to include to]\n[Enter task name]\n[Enter task description]" +
                    "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]");
            taskService.taskCreate(reader.readLine(), reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()), projectRepo);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
