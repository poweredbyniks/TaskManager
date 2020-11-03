package org.example.repository;

import lombok.Getter;
import lombok.Setter;


import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class HelpRepo {
    public static Map<String, String> commandMap = new HashMap<>();

    public Map<String, String> showAll(){
        return commandMap;
    }
    static {
        commandMap.put("project-create", "Creation of a project");
        commandMap.put("project-list", "List of the existing projects");
        commandMap.put("project-remove", "Removes a project");
        commandMap.put("project-clear", "Removes all projects");
        commandMap.put("task-create", "Creation of a task");
        commandMap.put("task-list", "List of the existing tasks");
        commandMap.put("task-remove", "Removes a task");
        commandMap.put("task-clear", "Removes all tasks");
        commandMap.put("help", "Help command");

    }

}
