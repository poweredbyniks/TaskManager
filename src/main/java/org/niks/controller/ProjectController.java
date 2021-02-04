package org.niks.controller;

import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public void create(@RequestBody Project project) {
        projectService.create(project);
    }

    @GetMapping("/list")
    public List<Project> projectList() {
        return projectService.projectList();
    }

    @GetMapping("/listStringOrdered")
    public List<Project> projectList(String order) {
        return projectService.list(order);
    }

    @GetMapping("/listComparatorOrdered")
    public List<Project> projectList(ProjectSort order) {
        return projectService.list(order);
    }

    @DeleteMapping("/remove")
    public void removeProject(String name) {
        projectService.remove(name);
    }

    @DeleteMapping("/clear")
    public void clearProject() {
        projectService.clear();
    }

    @GetMapping("/search")
    public List<Project> projectSearch(String source) {
        return projectService.projectSearch(source);
    }

    @GetMapping("/findExactMatch")
    public Project findExactMatch(String name) {
        return projectService.findExactMatch(name);
    }

}
