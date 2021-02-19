package org.niks.controller;

import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody String projectName, @RequestBody String projectDescription,
                       @RequestBody String startDate, @RequestBody String finishDate) {
        projectService.create(projectName, projectDescription,
                startDate, finishDate);
    }

    @GetMapping("/projects")
    public List<Project> list() {
        return projectService.list();
    }

    @GetMapping("/projects/{name}")
    public Project findProject(@PathVariable String name) {
        return projectService.findExactMatch(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/projects/{name}")
    public void remove(@PathVariable String name) {
        projectService.remove(name);
    }
}
