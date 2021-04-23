package org.niks.controller;

import org.niks.DTO.ProjectDto;
import org.niks.assembler.ProjectAssembler;
import org.niks.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project-management")
public class ProjectController {

    private final IProjectService projectService;
    private final ProjectAssembler projectAssembler;

    @Autowired
    public ProjectController(IProjectService projectService, ProjectAssembler projectAssembler) {
        this.projectService = projectService;
        this.projectAssembler = projectAssembler;
    }

    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody ProjectDto projectDto) {
        projectService.create(projectAssembler.fromDto(projectDto));
    }

    @GetMapping("/projects")
    public List<ProjectDto> list(@RequestParam(value = "order", required = false) String order) {
        return projectService.list(order)
                .stream()
                .map(ProjectDto::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/projects/{projectID}")
    public ProjectDto findByID(@PathVariable Long projectID) {
        return ProjectDto.fromDomain(projectService.findByID(projectID));
    }

    @GetMapping("/projects/{word}")
    public List<ProjectDto> findProjectByWord(@PathVariable String word) {
        return projectService.projectSearch(word)
                .stream()
                .map(ProjectDto::fromDomain)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("projects")
    public void update(@RequestBody ProjectDto projectDto) {
        projectService.update(projectAssembler.fromDto(projectDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/projects/{projectID}")
    public void remove(@PathVariable Long projectID) {
        projectService.remove(projectID);
    }
}