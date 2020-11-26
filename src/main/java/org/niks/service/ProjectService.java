package org.niks.service;

import lombok.Value;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.ProjectRepo;

import java.text.SimpleDateFormat;
import java.util.List;

@Value
public class ProjectService extends Service <Project>{
    ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public void create(Project project) {
        if (projectRepo.save(project)) {
            System.out.println("[Project " + project.getProjectName() + " created]");
        }
    }

    public void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<Project> projectList = projectRepo.findAll();
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

    public void remove(String projectToRemove) {
        projectRepo.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public void clear() {
        projectRepo.removeAll();
        System.out.println("[Project list is plain empty]");
    }
}
