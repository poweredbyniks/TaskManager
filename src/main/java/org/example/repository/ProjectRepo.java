package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProjectRepo {
    private Map<String, Project> projectMap = new HashMap<>();

    public Map<String, Project> showAll() {
        return projectMap;
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
