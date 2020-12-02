package org.niks.service;

import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.ProjectRepository;

import java.text.SimpleDateFormat;
import java.util.List;

public interface ProjectService {


    public static void create(Project project) {
        if(!project.getProjectName().equals("")) {
            if (ProjectRepository.save(project)) {
                System.out.println("[Project " + project.getProjectName() + " created]");
            }
        } else {
            System.out.println("Enter valid project name and try again");
        }
    }

    public static void list() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<Project> projectList = ProjectRepository.findAll();
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

    public static void remove(String projectToRemove) {
        ProjectRepository.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public static void clear() {
        ProjectRepository.removeAll();
        System.out.println("[Project list is plain empty]");
    }
}
