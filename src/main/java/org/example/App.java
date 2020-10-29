package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class App {
    static Map<String, Project> projectMap = new HashMap<>();
    static Map<String, Task> taskMap = new HashMap<>();
    static Map<String, String> commandMap = new HashMap<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) throws IOException {
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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (input != null) {
                switch (input) {
                    case "exit":
                        break;
                    case "project-create":
                        System.out.println("[Enter project name]\n[Enter project description]" +
                                "\n[Enter starting date dd.MM.yyyy]\n[Enter ending finishing date dd.MM.yyyy]");
                        projectCreate(reader.readLine(), reader.readLine(), dateFormat.parse(reader.readLine()), dateFormat.parse(reader.readLine()));
                        break;
                    case "project-list":
                        projectList();
                        break;
                    case "project-remove":
                        projectRemove(reader.readLine());
                        break;
                    case "project-clear":
                        projectClear();
                        break;
                    case "task-create":
                        System.out.println("[Enter task name]");
                        taskCreate(reader.readLine(), reader.readLine());
                        break;
                    case "task-list":
                        taskList();
                        break;
                    case "task-remove":
                        taskRemove(reader.readLine());
                        break;
                    case "task-clear":
                        taskClear();
                        break;
                    case "help":
                        helpCommand();
                }
                input = reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void projectCreate(String projectName, String projectDescription, Date startDate, Date finishDate) {
        Project project = new Project(UUID.randomUUID(), projectName, projectDescription, startDate, finishDate, null);
        System.out.println("[Project " + projectName + " created]");
        projectMap.put(projectName, project);
    }

    private static void projectList() {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            System.out.println("Project Name: " + projectEntry.getValue().getProjectName()
                    + "\nDescription: " + projectEntry.getValue().getProjectDescription()
                    + "\nStart date: " + dateFormat.format(projectEntry.getValue().getStartDate())
                    + "\nFinish date: " + dateFormat.format(projectEntry.getValue().getFinishDate()));
        }
    }

    private static void projectRemove(String projectToRemove) {
        projectMap.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    private static void projectClear() {
        projectMap.clear();
        System.out.println("[Project list is plain empty]");
    }

    private static void taskCreate(String taskName, String projectName) {
        Task task = new Task(taskName, projectName);
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getKey().equals(projectName)) {
                projectEntry.getValue().getTaskArray().add(task); //NPE
            } else System.out.println("No such existing projects");
        }
        System.out.println("[Task " + taskName + " created and added to project " + projectName + " ]");
        taskMap.put(taskName, task);
    }

    private static void taskList() {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            System.out.println("[ " + taskEntry.getKey() + " in the project " + taskEntry.getValue());
        }

        for (String key : taskMap.keySet()) {
            System.out.println("[ " + key + " ]");
        }
    }

    private static void taskRemove(String taskToRemove) {
        taskMap.remove(taskToRemove);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    private static void taskClear() {
        taskMap.clear();
        System.out.println("[Task list is empty]");
    }

    private static void helpCommand() {
        for (Map.Entry<String, String> commandsMap : commandMap.entrySet()) {
            System.out.println(commandsMap.getKey() + " : " + commandsMap.getValue());
        }
    }

}


