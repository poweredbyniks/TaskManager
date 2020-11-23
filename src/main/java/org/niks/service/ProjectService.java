package org.niks.service;


import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.repository.ProjectRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProjectService {
    private ProjectRepo projectRepo;
    private UserService userService;

    public ProjectService(ProjectRepo projectRepo, UserService userService) {
        this.projectRepo = projectRepo;
        this.userService = userService;
    }

    public void projectCreate(String projectName, String projectDescription, Date startDate, Date finishDate, long userID) {
        Project project = new Project(randomNumber(), projectName, projectDescription, startDate, finishDate, new ArrayList<>(), userID);
        projectRepo.save(project);
        if (projectRepo.save(project)) {
            System.out.println("[Project " + projectName + " created]");
        }
    }

    public void projectList() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            for(Project project : projectRepo.findAll()){
            System.out.println("Project Name: " + project.getProjectName()
                    + "\nDescription: " + project.getProjectDescription()
                    + "\nStart date: " + dateFormat.format(project.getStartDate())
                    + "\nFinish date: " + dateFormat.format(project.getFinishDate()));
            if (project.getTaskList().size() != 0) {
                for (int i = 0; i < projectRepo.findAll().size(); i++) {
                    System.out.println("\nTasks: \n" + "taskID: " + project.getTaskList().get(i).getTaskID()
                            + "\nTask name: " + project.getTaskList().get(i).getTaskName()
                            + "\nTask description: " + project.getTaskList().get(i).getTaskDescription()
                            + "\nStart date: " + dateFormat.format(project.getTaskList().get(i).getStartDate())
                            + "\nFinish date " + dateFormat.format(project.getTaskList().get(i).getFinishDate()));
                }
            } else {
                System.out.println("Task list is empty");
            }
        }
    }

    public void projectRemove(String projectToRemove) {
        projectRepo.remove(projectToRemove, userService.getCurrentUser());
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public void projectClear() {
        projectRepo.removeAll(userService.getCurrentUser());
        System.out.println("[Project list is plain empty]");
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
