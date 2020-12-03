package org.niks.service;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.ProjectRepository;

import java.text.SimpleDateFormat;
import java.util.List;

@Value
public class ProjectService implements IProjectService{
    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void create(@NotNull Project project) {
        if(!project.getProjectName().equals("")) {
            if (projectRepository.save(project)) {
                System.out.println("Project " + project.getProjectName() + " created");
            }
        } else {
            System.out.println("Enter valid project name and try again");
        }
    }

    public void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<Project> projectList = projectRepository.findAll();
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

    public void remove(@NotNull String projectToRemove) {
        projectRepository.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public void clear() {
        projectRepository.removeAll();
        System.out.println("[Project list is plain empty]");
    }
}
