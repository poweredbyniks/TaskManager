package org.niks.controller;

import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-management")
public class ProjectController {

    private final IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Project project) {
        projectService.create(project);
    }

    @GetMapping("/projects")
    public List<Project> list(@RequestParam(value = "order", required = false) String order) {
        return projectService.list(order);
    }

    @GetMapping("/projects/{projectID}")
    public Project findByID(@PathVariable Long projectID) {
        return projectService.findByID(projectID);
    }

    @GetMapping("/projects/{word}")
    public List<Project> findProjectByWord(@PathVariable String word) {
        return projectService.projectSearch(word);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("projects")
    public void update(@RequestBody Project project) {
        projectService.update(project);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/projects/{projectID}")
    public void remove(@PathVariable Long projectID) {
        projectService.remove(projectID);
    }
}