package org.example.commands;

import org.example.service.TaskService;

public class TaskClearCommand extends Command {
    private TaskService taskService;

    public TaskClearCommand(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public String getName() {
        return "task-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all tasks";
    }

    @Override
    public void execute() {
        taskService.taskClear();
    }
}
