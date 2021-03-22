package org.niks.controller;

import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks-management")
public class TaskController {

    private final ITaskService taskService;

    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Task task) {
        taskService.create(task);
    }

    @GetMapping("/tasks")
    public List<Task> list(@RequestParam(value = "order", required = false) String order) {
        return taskService.list(order);
    }

    @GetMapping("/tasks/{projectID}")
    public List<Task> list(@PathVariable long projectID) {
        return taskService.list(projectID);
    }

    @GetMapping("/tasks/{name}")
    public Task findTask(@PathVariable String name) {
        return taskService.findExactMatch(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("tasks")
    public void update(@RequestBody Task task) {
        taskService.update(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tasks/{name}")
    public void remove(@PathVariable String name) {
        taskService.remove(name);
    }
}