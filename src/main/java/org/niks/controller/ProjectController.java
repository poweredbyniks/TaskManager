package org.niks.controller;

import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.service.IProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public void create(@RequestBody Project project) {
        projectService.create(project);
    }

    @GetMapping("/listOrderedString")
    public List<Project> list(String order) {
        return projectService.list(order);
    }

    @GetMapping("/listOrderedComparator")
    public List<Project> list(ProjectSort order) {
        return projectService.list(order);
    }

    @DeleteMapping("/remove")
    public void remove(String name) {
        projectService.remove(name);
    }

    @DeleteMapping("/clear")
    public void clear() {
        projectService.clear();
    }

    @GetMapping("/search")
    public List<Project> search(String source) {
        return projectService.projectSearch(source);
    }

    @GetMapping("/findExactMatch")
    public Project findExactMatch(String name) {
        return projectService.findExactMatch(name);
    }
}
