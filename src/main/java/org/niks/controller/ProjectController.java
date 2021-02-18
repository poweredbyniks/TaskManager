package org.niks.controller;

import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-management")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public String create(@RequestBody String projectName, @RequestBody String projectDescription,
                         @RequestBody String startDate, @RequestBody String finishDate) {
        projectService.create(projectName, projectDescription,
                startDate, finishDate);
        return "Project " + projectName + " created";
    }

    @GetMapping("/projects")
    public List<Project> list() {
        return projectService.list();
    }

    @GetMapping("projects/{name}")
    public String projectDescription(@PathVariable String name) {
        return projectService.findExactMatch(name).getProjectDescription();
    }

    @DeleteMapping("/projects/{name}")
    public String remove(@PathVariable String name) {
        projectService.remove(name);
        return name + " removed";
    }
}
