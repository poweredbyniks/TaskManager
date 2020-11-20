package org.example.service;

import org.example.entity.Task;
import org.example.entity.User;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TaskService {
    private TaskRepo taskRepo;
    private ProjectRepo projectRepo;

    public TaskService(TaskRepo taskRepo, ProjectRepo projectRepo) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
    }

    public void taskCreate(String projectName, String taskName, String taskDescription, Date startDate,
                           Date finishDate, ProjectRepo projectRepo, User user) {
        Task task = new Task(randomNumber(), taskName, projectName, taskDescription, startDate, finishDate, user.getUserID());

        if (projectRepo.showAll(user).containsKey(projectName)) {
            projectRepo.showAll(user).get(projectName).getTaskList().add(task);
            taskRepo.save(task);
            if (taskRepo.save(task)) {
                System.out.println("[Task " + taskName + " created and added to the project " + projectName + "]");
            }
            taskRepo.save(task);
        } else {
            System.out.println("No such existing projects");
        }
    }

    public void taskList(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Map.Entry<String, Task> taskEntry : taskRepo.showAll(user).entrySet()) {
            System.out.println("[Task " + taskEntry.getKey() + " in the project " + taskEntry.getValue().getProjectName() + "]" +
                    "\nStart date: " + dateFormat.format(taskEntry.getValue().getStartDate()) +
                    "\nFinish date: " + dateFormat.format(taskEntry.getValue().getFinishDate()));
        }
    }

    public void taskRemove(String taskToRemove, User user) {
        taskRepo.remove(taskToRemove, user);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    public void taskClear(User user) {
        taskRepo.removeAll(user);
        System.out.println("[Task list is empty]");
    }


    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
