package org.example;

import org.example.repository.HelpRepo;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;
import org.example.service.HelpService;
import org.example.service.ProjectService;
import org.example.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bootstrap {

    ProjectRepo projectRepo = new ProjectRepo();
    TaskRepo taskRepo = new TaskRepo();
    ProjectService projectService = new ProjectService(projectRepo);
    TaskService taskService = new TaskService(taskRepo, projectRepo);
    HelpRepo helpRepo = new HelpRepo();
    HelpService helpService = new HelpService(helpRepo);

    public void init() {
        System.out.println("Welcome to the Task Manager.\nType help to get instructions");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (input != null) {
                switch (input) {
                    case "exit":
                        break;
                    case "project-create":
                        System.out.println("[Enter project name]\n[Enter project description]" +
                                "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]");
                        projectService.projectCreate(reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
                        break;
                    case "project-list":
                        projectService.projectList();
                        break;
                    case "project-remove":
                        projectService.projectRemove(reader.readLine());
                        break;
                    case "project-clear":
                        projectService.projectClear();
                        break;
                    case "task-create":
                        System.out.println("[Enter project to include to]\n[Enter task name]\n[Enter task description]" +
                                "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]");
                        taskService.taskCreate(reader.readLine(), reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()), projectRepo);
                        break;
                    case "task-list":
                        taskService.taskList();
                        break;
                    case "task-remove":
                        taskService.taskRemove(reader.readLine());
                        break;
                    case "task-clear":
                        taskService.taskClear();
                        break;
                    case "help":
                        helpService.getCommands();
                }
                input = reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
