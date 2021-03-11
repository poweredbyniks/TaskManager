package org.niks.controller;

import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
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
        try {
            projectService.create(project);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Project not created", throwables);
        }
    }

    @GetMapping("/projects")
    public List<Project> list(@RequestParam(value = "order", required = false) String order) {
        try {
            return projectService.list(order);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projects not found", throwables);
        }
    }

    @GetMapping("/projects/{name}")
    public Project findProject(@PathVariable String name) {
        try {
            return projectService.findExactMatch(name);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found", throwables);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("projects")
    public void update(@RequestBody Project project) {
        try {
            projectService.update(project);
        } catch (SQLException throwables){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Project update failed", throwables);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/projects/{name}")
    public void remove(@PathVariable String name) {
        try {
            projectService.remove(name);
        } catch (SQLException throwables){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Project remove failed", throwables);
        }
    }
}