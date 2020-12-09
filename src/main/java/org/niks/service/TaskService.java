package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.ITaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public final class TaskService implements ITaskService {
    private final ITaskRepository iTaskRepository;

    public void create(@NotNull final Task task) {
        if (!task.getTaskName().equals("")) {
            if (iTaskRepository.save(task)) {
                System.out.println("Task " + task.getTaskName() + " created and added to the project " + task.getProjectName());
            } else {
                System.out.println("No such existing project");
            }
        } else {
            System.out.println("Enter valid task name and try again");
        }
    }

    public void list(@NotNull final BufferedReader reader) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final List<Task> taskList = iTaskRepository.findAll();
        System.out.println("Order by");
        try {
            String order = reader.readLine();
            if (order.equals("")) {
                System.out.println("Ordered by creation date");
            } else {
                if (order.equals("start date")) {
                    taskList.sort(CompareByStartDate);
                }
                if (order.equals("finish date")) {
                    taskList.sort(CompareByFinishDate);
                }
                if (order.equals("status")) {
                    taskList.sort(CompareByStatus);
                }
                System.out.println("Ordered by " + order);
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        for (Task task : taskList) {
            System.out.println("Task " + task.getTaskName() + " in the project " + task.getProjectName() +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }

    public void remove(@NotNull final String taskToRemove) {
        iTaskRepository.remove(taskToRemove);
        System.out.println("Task " + taskToRemove + "removed");
    }

    public void clear() {
        iTaskRepository.removeAll();
        System.out.println("Task list is empty");
    }

    public static Comparator<Task> CompareByStartDate = Comparator.comparing(Task::getStartDate);
    public static Comparator<Task> CompareByFinishDate = Comparator.comparing(Task::getFinishDate);
    public static Comparator<Task> CompareByStatus = Comparator.comparing(Task::getTaskStatus);
}
