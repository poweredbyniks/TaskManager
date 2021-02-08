package org.niks.controller;

import org.niks.TaskSort;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public void create(@RequestBody Task task) {
        taskService.create(task);
    }

    @GetMapping("/listOrderedString")
    public List<Task> list(String order) {
        return taskService.list(order);
    }

    @GetMapping("/listOrderedComparator")
    public List<Task> list(TaskSort order) {
        return taskService.list(order);
    }

    @DeleteMapping("/remove")
    public void remove(String name) {
        taskService.remove(name);
    }

    @DeleteMapping("/clear")
    public void clear() {
        taskService.clear();
    }

    @GetMapping("/search")
    public List<Task> projectSearch(String source) {
        return taskService.taskSearch(source);
    }

    @GetMapping("/findExactMatch")
    public Task findExactMatch(String name) {
        return taskService.findExactMatch(name);
    }
}
