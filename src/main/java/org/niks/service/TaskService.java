package org.niks.service;

import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.repository.ProjectRepo;
import org.niks.repository.TaskRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TaskService {
    private TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
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
            System.out.println("No such existing project");
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
