package org.niks.controller;

import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/tasks-managment")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody String taskName, @RequestBody String projectName,
                       @RequestBody String taskDescription, @RequestBody String startDate,
                       @RequestBody String finishDate) {
        taskService.create(taskName, projectName, taskDescription, startDate, finishDate);

    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return taskService.list();
    }

    @GetMapping("/tasks/{name}")
    public Task findTask(@PathVariable String name) {
        return taskService.findExactMatch(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tasks/{name}")
    public void remove(@PathVariable String name) {
        taskService.remove(name);
    }
}
