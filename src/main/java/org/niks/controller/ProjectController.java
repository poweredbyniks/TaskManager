package org.niks.controller;

import org.jetbrains.annotations.NotNull;
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
    public void create(@RequestBody Project project) {
        projectService.create(project);
    }

    @GetMapping("/projects")
    public List<Project> list(@RequestParam(value = "order", required = false) @NotNull final String order) {
        return projectService.list(order);
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
