package org.niks.service;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.repository.TaskRepository;

import java.text.SimpleDateFormat;

@Value
public class TaskService implements ITaskService {
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(@NotNull Task task) {
        if (!task.getTaskName().equals("")) {
            if (taskRepository.save(task)) {
                System.out.println("[Task " + task.getTaskName() + " created and added to the project " + task.getProjectName() + "]");
            } else {
                System.out.println("No such existing project");
            }
        } else {
            System.out.println("Enter valid task name and try again");
        }
    }

    public void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : taskRepository.findAll()) {
            System.out.println("[Task " + task.getTaskName() + " in the project " + task.getProjectName() + "]" +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }

    public void remove(@NotNull String taskToRemove) {
        taskRepository.remove(taskToRemove);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    public void clear() {
        taskRepository.removeAll();
        System.out.println("[Task list is empty]");
    }
}
