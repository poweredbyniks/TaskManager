package org.example.service;


import org.example.entity.Project;
import org.example.entity.User;
import org.example.repository.ProjectRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ProjectService {
    private ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public void projectCreate(String projectName, String projectDescription, Date startDate, Date finishDate, long userID) {
        Project project = new Project(randomNumber(), projectName, projectDescription, startDate, finishDate, new ArrayList<>(), userID);
        projectRepo.save(project);
        if (projectRepo.save(project)) {
            System.out.println("[Project " + projectName + " created]");
        }
    }

    public void projectList(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Map.Entry<String, Project> projectEntry : projectRepo.showAll(user).entrySet()) {
            System.out.println("Project Name: " + projectEntry.getValue().getProjectName()
                    + "\nDescription: " + projectEntry.getValue().getProjectDescription()
                    + "\nStart date: " + dateFormat.format(projectEntry.getValue().getStartDate())
                    + "\nFinish date: " + dateFormat.format(projectEntry.getValue().getFinishDate())
                    + "\nTasks: " + projectEntry.getValue().getTaskList());
        }
    }

    public void projectRemove(String projectToRemove, User user) {
        projectRepo.remove(projectToRemove, user);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public void projectClear(User user) {
        projectRepo.removeAll(user);
        System.out.println("[Project list is plain empty]");
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }
}
