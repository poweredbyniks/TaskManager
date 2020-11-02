package org.example;

import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;
import org.example.service.ProjectService;
import org.example.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Bootstrap {
    public static Map<String, String> commandMap = new HashMap<>();
    ProjectRepo projectRepo = new ProjectRepo();
    TaskRepo taskRepo = new TaskRepo();
    public void init(){
        commandMap.put("project-create", "Creation of a project");
        commandMap.put("project-list", "List of the existing projects");
        commandMap.put("project-remove", "Removes a project");
        commandMap.put("project-clear", "Removes all projects");
        commandMap.put("task-create", "Creation of a task");
        commandMap.put("task-list", "List of the existing tasks");
        commandMap.put("task-remove", "Removes a task");
        commandMap.put("task-clear", "Removes all tasks");
        commandMap.put("help", "Help command");
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
                        ProjectService.projectCreate(reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
                        break;
                    case "project-list":
                        ProjectService.projectList();
                        break;
                    case "project-remove":
                        ProjectService.projectRemove(reader.readLine());
                        break;
                    case "project-clear":
                        ProjectService.projectClear();
                        break;
                    case "task-create":
                        System.out.println("[Enter project to include to]\n[Enter task name]\n[Enter task description]" +
                                "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]");
                        TaskService.taskCreate(reader.readLine(), reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
                        break;
                    case "task-list":
                        TaskService.taskList();
                        break;
                    case "task-remove":
                        TaskService.taskRemove(reader.readLine());
                        break;
                    case "task-clear":
                        TaskService.taskClear();
                        break;
                    case "help":
                        TaskService.helpCommand();
                }
                input = reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
