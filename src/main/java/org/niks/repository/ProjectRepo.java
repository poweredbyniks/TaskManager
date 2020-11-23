package org.niks.repository;

import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProjectRepo {
    private Map<String, Project> projectMap = new HashMap<>();
    private UserService userService;
    public ProjectRepo(UserService userService) {
        this.userService = userService;
    }

    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == userService.getCurrentUser().getUserID()) {
                projectList.add(projectEntry.getValue());
            }
        }
        return projectList;
    }

    public Project findOne(String name) {
        Project project = null;
        if (projectMap.get(name).getUserID() == userService.getCurrentUser().getUserID()) {
            project = projectMap.get(name);
        }
        return project;
    }

    public boolean save(Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public boolean update(Project project) {
        return false;
    }

    public void remove(String name, User user) {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == user.getUserID()) {
                projectMap.remove(name);
            }
        }
    }

    public void removeAll(User user) {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == user.getUserID()) {
                projectMap.remove(projectEntry.getKey());
            }
        }
    }
}
