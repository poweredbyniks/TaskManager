package org.example.commands;

import org.example.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskRemoveCommand extends Command {
    private TaskService taskService;

    public TaskRemoveCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public String getName() {
        return "task-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a task";
    }

    @Override
    public void execute() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            taskService.taskRemove(reader.readLine());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
