package org.niks.service;

import lombok.Value;
import org.niks.entity.Task;
import org.niks.repository.TaskRepo;

import java.text.SimpleDateFormat;

@Value
public class TaskService extends Service <Task> {
    TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void create(Task task) {
        if (taskRepo.save(task)) {
            System.out.println("[Task " + task.getTaskName() + " created and added to the project " + task.getProjectName() + "]");
        } else {
            System.out.println("No such existing project");
        }
    }

    public void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : taskRepo.findAll()) {
            System.out.println("[Task " + task.getTaskName() + " in the project " + task.getProjectName() + "]" +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }

    public void remove(String taskToRemove) {
        taskRepo.remove(taskToRemove);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    public void clear() {
        taskRepo.removeAll();
        System.out.println("[Task list is empty]");
    }


}
