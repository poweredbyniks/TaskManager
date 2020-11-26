package org.niks.service;

import org.niks.entity.Task;
import org.niks.repository.TaskRepo;

import java.text.SimpleDateFormat;

public class TaskService {
    private TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void taskCreate(Task task) {
        if (taskRepo.save(task)) {
            System.out.println("[Task " + task.getTaskName() + " created and added to the project " + task.getProjectName() + "]");
        } else {
            System.out.println("No such existing project");
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


}
