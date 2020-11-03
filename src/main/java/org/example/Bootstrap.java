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
    HelpRepo helpRepo = new HelpRepo();

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
                        ProjectService projectCreateService = new ProjectService(projectRepo);
                        projectCreateService.projectCreate(reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
                        break;
                    case "project-list":
                        ProjectService projectListService = new ProjectService(projectRepo);
                        projectListService.projectList();
                        break;
                    case "project-remove":
                        ProjectService projectRemoveService = new ProjectService(projectRepo);
                        projectRemoveService.projectRemove(reader.readLine());
                        break;
                    case "project-clear":
                        ProjectService projectClearService = new ProjectService(projectRepo);
                        projectClearService.projectClear();
                        break;
                    case "task-create":
                        System.out.println("[Enter project to include to]\n[Enter task name]\n[Enter task description]" +
                                "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]");
                        TaskService taskCreateService = new TaskService(taskRepo);
                        taskCreateService.taskCreate(reader.readLine(), reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
                        break;
                    case "task-list":
                        TaskService taskListService = new TaskService(taskRepo);
                        taskListService.taskList();
                        break;
                    case "task-remove":
                        TaskService taskRemoveService = new TaskService(taskRepo);
                        taskRemoveService.taskRemove(reader.readLine());
                        break;
                    case "task-clear":
                        TaskService taskClearService = new TaskService(taskRepo);
                        taskClearService.taskClear();
                        break;
                    case "help":
                        HelpService helpService = new HelpService(helpRepo);
                        helpService.getCommands();
                }
                input = reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
