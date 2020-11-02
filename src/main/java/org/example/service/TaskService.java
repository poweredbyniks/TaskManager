package org.example.service;

import org.example.Bootstrap;
import org.example.entity.Task;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

public class TaskService {
    private TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void taskCreate(String projectName, String taskName, String taskDescription, Date startDate, Date finishDate) {
        Task task = new Task(randomNumber(), taskName, projectName, taskDescription, startDate, finishDate);

        ProjectRepo projectRepo = new ProjectRepo();
        if (projectRepo.showAll().containsKey(projectName)) {
            projectRepo.showAll().get(projectName).getTaskList().add(task);

        } else {
            System.out.println("No such existing projects");
        }

        System.out.println("[Task " + taskName + " created and added to the project " + projectName + "]");

        taskRepo.save(task);
    }

    public void taskList() {
        TaskRepo taskRepo = new TaskRepo();
        for (Map.Entry<String, Task> taskEntry : taskRepo.showAll().entrySet()) {
            System.out.println("[Task " + taskEntry.getKey() + " in the project " + taskEntry.getValue().getProjectName() + "]");
        }
    }

    public void taskRemove(String taskToRemove) {
        taskRepo.remove(taskToRemove);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    public void taskClear() {
        taskRepo.removeAll();
        System.out.println("[Task list is empty]");
    }

    public void helpCommand() {
        for (Map.Entry<String, String> commandsMap : Bootstrap.commandMap.entrySet()) {
            System.out.println(commandsMap.getKey() + " : " + commandsMap.getValue());
        }
    }

    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
