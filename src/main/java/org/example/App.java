package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        Map<String, Project> projectMap = new HashMap<>();
        Map<String, Task> taskMap = new HashMap<>();
        Map<String, String> commandMap = new HashMap<>();
        System.out.println("Welcome to the Task Manager.\nType help to get instructions");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (input != null) {
                switch (input) {
                    case "exit":
                        break;
                    case "project-create":
                        System.out.println("[Enter project name]");
                        String projectName = reader.readLine();
                        Project project = new Project(projectName);
                        System.out.println("[Project " + projectName + " created]");
                        projectMap.put(projectName, project);
                        break;
                    case "project-list":
                        for (String key : projectMap.keySet()) {
                            System.out.println("[ " + key + " ]");
                        }
                        break;
                    case "project-remove":
                        String projectToRemove = reader.readLine();
                        projectMap.remove(projectToRemove);
                        System.out.println("[Project " + projectToRemove + " removed]");
                        break;
                    case "project-clear":
                        projectMap.clear();
                        System.out.println("[Project list is plain empty]");
                        break;
                    case "task-create":
                        System.out.println("[Enter task name]");
                        String taskName = reader.readLine();
                        Task task = new Task(taskName);
                        System.out.println("[Task " + taskName + " created]");
                        taskMap.put(taskName, task);
                        break;
                    case "task-list":
                        for (String key : taskMap.keySet()) {
                            System.out.println("[ " + key + " ]");
                        }
                        break;
                    case "task-remove":
                        String taskToRemove = reader.readLine();
                        taskMap.remove(taskToRemove);
                        System.out.println("[Task " + taskToRemove + "removed]");
                        break;
                    case "task-clear":
                        taskMap.clear();
                        System.out.println("[Task list is empty]");
                        break;
                    case "help":
                        commandMap.put("project-create", "Creation of a project");
                        commandMap.put("project-list", "List of the existing projects");
                        commandMap.put("project-remove", "Removes a project");
                        commandMap.put("project-clear", "Removes all projects");
                        commandMap.put("task-create", "Creation of a task");
                        commandMap.put("task-list", "List of the existing tasks");
                        commandMap.put("task-remove", "Removes a task");
                        commandMap.put("task-clear", "Removes all tasks");
                        for (Map.Entry<String, String> commands : commandMap.entrySet()) {
                            System.out.println(commands.getKey() + " : " + commands.getValue());
                        }
                }
                input = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
