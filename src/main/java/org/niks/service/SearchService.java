package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;
import org.niks.repository.ITaskRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SearchService {
    private final IProjectRepository iProjectRepository;
    private final ITaskRepository iTaskRepository;

    public void projectSearch(@NotNull final String source) {
        List<Project> projectList = iProjectRepository.findAll();
        List<Project> foundProjectList = new ArrayList<>();
        for (Project project : projectList) {
            if (project.getProjectName().toLowerCase().contains(source) | project.getProjectDescription().toLowerCase().contains(source)) {
                foundProjectList.add(project);
            }
        }
        if (foundProjectList.isEmpty()) {
            System.out.println("Project not found");
        }
        for (Project project : foundProjectList) {
            System.out.println(project.getProjectName());
        }
    }

    public void taskSearch(@NotNull final String source) {
        List<Task> taskList = iTaskRepository.findAll();
        List<Task> foundTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(source) | task.getTaskDescription().toLowerCase().contains(source)) {
                foundTaskList.add(task);
            }
        }
        if (foundTaskList.isEmpty()) {
            System.out.println("Task not found");
        }
        for (Task task : foundTaskList) {
            System.out.println(task.getTaskName());
        }
    }
}

