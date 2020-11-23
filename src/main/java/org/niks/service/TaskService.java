package org.niks.service;

import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.ProjectRepo;
import org.niks.repository.TaskRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskService {
    private TaskRepo taskRepo;
    private UserService userService;

    public TaskService(TaskRepo taskRepo, UserService userService) {
        this.taskRepo = taskRepo;
        this.userService = userService;
    }

    public void taskCreate(String projectName, String taskName, String taskDescription, Date startDate,
                           Date finishDate, ProjectRepo projectRepo) {
        Task task = new Task(randomNumber(), taskName, projectName, taskDescription, startDate, finishDate, userService.getCurrentUser().getUserID());
        for (Project project : projectRepo.findAll()) {
            if (project.getProjectName().equals(projectName)) {
                projectRepo.findOne(projectName).getTaskList().add(task);
                taskRepo.save(task);
                if (taskRepo.save(task)) {
                    System.out.println("[Task " + taskName + " created and added to the project " + projectName + "]");
                }
                taskRepo.save(task);
            } else {
                System.out.println("No such existing project");
            }
        }
    }

    public void taskList() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : taskRepo.findAll()) {
            System.out.println("[Task " + task.getTaskName() + " in the project " + task.getProjectName() + "]" +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
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
