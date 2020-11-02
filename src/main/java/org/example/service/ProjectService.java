package org.example.service;


import org.example.entity.Project;
import org.example.repository.ProjectRepo;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ProjectService {


    public ProjectService(ProjectRepo projectRepo) {

    }

    public static void projectCreate(String projectName, String projectDescription, Date startDate, Date finishDate) {
        Project project = new Project(randomNumber(), projectName, projectDescription, startDate, finishDate, new ArrayList<>());
        System.out.println("[Project " + projectName + " created]");
        ProjectRepo.projectMap.put(projectName, project);
    }

    public static void projectList() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Map.Entry<String, Project> projectEntry : ProjectRepo.projectMap.entrySet()) {
            System.out.println("Project Name: " + projectEntry.getValue().getProjectName()
                    + "\nDescription: " + projectEntry.getValue().getProjectDescription()
                    + "\nStart date: " + dateFormat.format(projectEntry.getValue().getStartDate())
                    + "\nFinish date: " + dateFormat.format(projectEntry.getValue().getFinishDate())
                    + "\nTasks: " + projectEntry.getValue().getTaskList());
        }
    }

    public static void projectRemove(String projectToRemove) {
        ProjectRepo.projectMap.remove(projectToRemove);
        System.out.println("[Project " + projectToRemove + " removed]");
    }

    public static void projectClear() {
        ProjectRepo.projectMap.clear();
        System.out.println("[Project list is plain empty]");
    }

    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt() + 1000;
    }
}
