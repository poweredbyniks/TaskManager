package org.example;

import org.example.commands.*;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;
import org.example.service.ProjectService;
import org.example.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap {

    ProjectRepo projectRepo = new ProjectRepo();
    TaskRepo taskRepo = new TaskRepo();
    ProjectService projectService = new ProjectService(projectRepo);
    TaskService taskService = new TaskService(taskRepo, projectRepo);
    public static Map<String, Command> commandMap = new LinkedHashMap<>();

    HelpCommand helpCommand = new HelpCommand();
    ProjectClearCommand projectClearCommand = new ProjectClearCommand();
    ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand();
    ProjectListCommand projectListCommand = new ProjectListCommand();
    ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand();
    TaskClearCommand taskClearCommand = new TaskClearCommand();
    TaskCreateCommand taskCreateCommand = new TaskCreateCommand();
    TaskListCommand taskListCommand = new TaskListCommand();
    TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand();
    public void init() {

        commandMap.put(helpCommand.getName(), helpCommand);
        commandMap.put(projectCreateCommand.getName(), projectCreateCommand);
        commandMap.put(projectListCommand.getName(), projectListCommand);
        commandMap.put(projectRemoveCommand.getName(), projectRemoveCommand);
        commandMap.put(projectClearCommand.getName(), projectClearCommand);
        commandMap.put(taskCreateCommand.getName(), taskCreateCommand);
        commandMap.put(taskListCommand.getName(), taskListCommand);
        commandMap.put(taskRemoveCommand.getName(), taskRemoveCommand);
        commandMap.put(taskClearCommand.getName(), taskClearCommand);


        System.out.println("Welcome to the Task Manager.\nType help to get instructions");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (input != null) {
                switch (input) {
                    case "exit":
                        break;
                    case "project-create":
                        projectCreateCommand.execute();
                        break;
                    case "project-list":
                        projectListCommand.execute();
                        break;
                    case "project-remove":
                        projectRemoveCommand.execute();
                        break;
                    case "project-clear":
                        projectClearCommand.execute();
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
                        helpCommand.execute();
                }
                input = reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
