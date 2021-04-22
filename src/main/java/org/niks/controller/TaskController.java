package org.niks.controller;

import org.niks.DTO.TaskDto;
import org.niks.assembler.TaskAssembler;
import org.niks.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks-management")
public class TaskController {

    private final ITaskService taskService;
    private final TaskAssembler taskAssembler;

    @Autowired
    public TaskController(ITaskService taskService, TaskAssembler taskAssembler) {
        this.taskService = taskService;
        this.taskAssembler = taskAssembler;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody TaskDto taskDto) {
        taskService.create(taskAssembler.fromDto(taskDto));
    }

    @GetMapping("/tasks")
    public List<TaskDto> list(@RequestParam(value = "order", required = false) String order) {
        return taskService.list(order)
                .stream()
                .map(TaskDto::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/{projectID}")
    public List<TaskDto> list(@PathVariable Long projectID) {
        return taskService.list(projectID)
                .stream()
                .map(TaskDto::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/{taskID}")
    public TaskDto findByID(@PathVariable Long taskID) {
        return TaskDto.fromDomain(taskService.findByID(taskID));
    }

    @GetMapping("/tasks/{word}")
    public List<TaskDto> findTask(@PathVariable String word) {
        return taskService.taskSearch(word)
                .stream()
                .map(TaskDto::fromDomain)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("tasks")
    public void update(@RequestBody TaskDto taskDto) {
        taskService.update(taskAssembler.fromDto(taskDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tasks/{ID}")
    public void remove(@PathVariable Long ID) {
        taskService.remove(ID);
    }
}