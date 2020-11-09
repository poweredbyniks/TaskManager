package org.example.commands;

import org.example.service.TaskService;

public class TaskListCommand extends Command{
    private TaskService taskService;

    public TaskListCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "task-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing tasks";
    }

    @Override
    public void execute() {
    taskService.taskList();
    }
}
