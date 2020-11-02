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
    private static Map<String, Project> projectMap = new HashMap<>();

    public Map<String, Project> showAll(){
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
        projectMap.put(project.getProjectName(), new Project(project.getProjectID(),
                project.getProjectName(), project.getProjectDescription(),
                project.getStartDate(), project.getFinishDate(), project.getTaskList()));
        return true;
    }

    public void merge() {

    }

    public void remove(String name) {
        projectMap.remove(name);
    }

    public void removeAll() {
        projectMap.clear();
    }
}
