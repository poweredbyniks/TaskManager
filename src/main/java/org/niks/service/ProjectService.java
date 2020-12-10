package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public final class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public void create(@NotNull final Project project) {
        if (!project.getProjectName().equals("")) {
            if (projectRepository.save(project)) {
                System.out.println("Project " + project.getProjectName() + " created");
            }
        } else {
            System.out.println("Enter valid project name and try again");
        }
    }

    public void list(@NotNull final BufferedReader reader) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final List<Project> projectList = projectList();
        System.out.println("Order by creation date\nstart date\nfinish date\nstatus");
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
        projectRepository.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public void clear() {
        projectRepository.removeAll();
        System.out.println("[Project list is plain empty]");
    }

    public void projectSearch(@NotNull final String source) {
        final List<Project> projectList = projectList();
        final List<Project> foundProjectList = new ArrayList<>();
        for (Project project : projectList) {
            if (project.getProjectName().toLowerCase().contains(source.toLowerCase()) | project.getProjectDescription().toLowerCase().contains(source.toLowerCase())) {
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

    public List<Project> projectList(){
        return projectRepository.findAll();
    }

    public static Comparator<Project> CompareByStartDate = Comparator.comparing(Project::getStartDate);
    public static Comparator<Project> CompareByFinishDate = Comparator.comparing(Project::getFinishDate);
    public static Comparator<Project> CompareByStatus = Comparator.comparing(Project::getProjectStatus);
}
