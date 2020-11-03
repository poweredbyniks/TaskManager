package org.example.service;

import org.example.Bootstrap;
import org.example.entity.Task;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        TaskRepo taskRepo = new TaskRepo();
        for (Map.Entry<String, Task> taskEntry : taskRepo.showAll().entrySet()) {
            System.out.println("[Task " + taskEntry.getKey() + " in the project " + taskEntry.getValue().getProjectName() + "]" +
                    "\nStart date: " + dateFormat.format(taskEntry.getValue().getStartDate()) +
                    "\nFinish date: " + dateFormat.format(taskEntry.getValue().getFinishDate()));
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


    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
