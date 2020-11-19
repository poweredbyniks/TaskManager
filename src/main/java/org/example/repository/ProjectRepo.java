package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Project;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProjectRepo {
    private Map<String, Project> projectMap = new HashMap<>();

    public Map<String, Project> showAll(User user) {
        Map<String, Project> userProjectMap = new HashMap<>();
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == user.getUserID()) {
                projectMap.put(projectEntry.getKey(), projectEntry.getValue());
            }
        }
        return userProjectMap;
    }

    public List<Project> findAll(List<String> names) {
        List<Project> projectList = new ArrayList<>();
        for (String name : names) {
            if (names.contains(name))
                projectList.add(projectMap.get(name));
        }
        return projectList;
    }

    public Project findOne(String name) {
        return projectMap.get(name);
    }

    public boolean save(Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public boolean update(Project project) {
        return false;
    }

    public void remove(String name) {
        projectMap.remove(name);
    }

    public void removeAll() {
        projectMap.clear();
    }
}
