package org.niks.service;

import org.niks.entity.Task;
import org.niks.repository.TaskRepository;

import java.text.SimpleDateFormat;

public interface TaskService {


    public static void create(Task task) {
        if(!task.getTaskName().equals("")) {
            if (TaskRepository.save(task)) {
                System.out.println("[Task " + task.getTaskName() + " created and added to the project " + task.getProjectName() + "]");
            } else {
                System.out.println("No such existing project");
            }
        } else {
            System.out.println("Enter valid task name and try again");
        }
    }

    public static void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : TaskRepository.findAll()) {
            System.out.println("[Task " + task.getTaskName() + " in the project " + task.getProjectName() + "]" +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }

    public static void remove(String taskToRemove) {
        TaskRepository.remove(taskToRemove);
        System.out.println("[Task " + taskToRemove + "removed]");
    }

    public static void clear() {
        TaskRepository.removeAll();
        System.out.println("[Task list is empty]");
    }
}
