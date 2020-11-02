package org.example.service;

import org.example.App;
import org.example.Bootstrap;
import org.example.entity.Task;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

public class TaskService {

    public TaskService(TaskRepo taskRepo) {

    }

    public static void taskCreate(String projectName, String taskName, String taskDescription, Date startDate, Date finishDate) {
        Task task = new Task(randomNumber(), taskName, projectName, taskDescription, startDate, finishDate);
        if (ProjectRepo.projectMap.containsKey(projectName)) {
            ProjectRepo.projectMap.get(projectName).getTaskList().add(task);

        } else System.out.println("No such existing projects");

        System.out.println("[Task " + taskName + " created and added to the project " + projectName + "]");
        TaskRepo.taskMap.put(taskName, task);
    }

    public static void taskList() {
        for (Map.Entry<String, Task> taskEntry : TaskRepo.taskMap.entrySet()) {
            System.out.println("[Task " + taskEntry.getKey() + " in the project " + taskEntry.getValue().getProjectName() + "]");
        }
    }

    public static void taskRemove(String taskToRemove) {
        TaskRepo.taskMap.remove(taskToRemove);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    public static void taskClear() {
        TaskRepo.taskMap.clear();
        System.out.println("[Task list is empty]");
    }

    public static void helpCommand() {
        for (Map.Entry<String, String> commandsMap : Bootstrap.commandMap.entrySet()) {
            System.out.println(commandsMap.getKey() + " : " + commandsMap.getValue());
        }
    }

    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt() + 1000;
    }
}
