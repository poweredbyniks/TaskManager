package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public final class ProjectService implements IProjectService {
    private final IProjectRepository iProjectRepository;

    public void create(@NotNull final Project project) {
        if (!project.getProjectName().equals("")) {
            if (iProjectRepository.save(project)) {
                System.out.println("Project " + project.getProjectName() + " created");
            }
        } else {
            System.out.println("Enter valid project name and try again");
        }
    }

    public void list(@NotNull final BufferedReader reader) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final List<Project> projectList = iProjectRepository.findAll();
        System.out.println("Order by");
        try {
            String order = reader.readLine();
            if (order.equals("")) {
                System.out.println("Ordered by creation date");
            } else {
                if (order.equals("start date")) {
                    projectList.sort(CompareByStartDate);
                }
                if (order.equals("finish date")) {
                    projectList.sort(CompareByFinishDate);
                }
                if (order.equals("status")) {
                    projectList.sort(CompareByStatus);
                }
                System.out.println("Ordered by " + order);
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        for (Project project : projectList) {
            System.out.println("Project Name: " + project.getProjectName()
                    + "\nDescription: " + project.getProjectDescription()
                    + "\nStart date: " + dateFormat.format(project.getStartDate())
                    + "\nFinish date: " + dateFormat.format(project.getFinishDate()));
            if (project.getTaskList().size() != 0) {
                for (Task task : project.getTaskList()) {
                    System.out.println("Tasks:"
                            + "\nTask name: " + task.getTaskName()
                            + "\nTask description: " + task.getTaskDescription()
                            + "\nStart date: " + dateFormat.format(task.getStartDate())
                            + "\nFinish date: " + dateFormat.format(task.getFinishDate()));
                }
            } else {
                System.out.println("Task list is empty");
            }
        }
    }

    public void remove(@NotNull final String projectToRemove) {
        iProjectRepository.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public void clear() {
        iProjectRepository.removeAll();
        System.out.println("[Project list is plain empty]");
    }

    public static Comparator<Project> CompareByStartDate = Comparator.comparing(Project::getStartDate);
    public static Comparator<Project> CompareByFinishDate = Comparator.comparing(Project::getFinishDate);
    public static Comparator<Project> CompareByStatus = Comparator.comparing(Project::getProjectStatus);
}
