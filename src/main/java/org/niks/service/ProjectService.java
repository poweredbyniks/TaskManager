package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;

import java.text.SimpleDateFormat;
import java.util.List;

@AllArgsConstructor
public final class ProjectService implements IProjectService<Project> {
    private final IProjectRepository<Project> iProjectRepository;

    public void create(@NotNull final Project project) {
        if (!project.getProjectName().equals("")) {
            if (iProjectRepository.save(project)) {
                System.out.println("Project " + project.getProjectName() + " created");
            }
        } else {
            System.out.println("Enter valid project name and try again");
        }
    }

    public void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final List<Project> projectList = iProjectRepository.findAll();
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
}
