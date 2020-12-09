package org.niks.commands;

import lombok.AllArgsConstructor;
import org.niks.service.IUserService;
import org.niks.service.SearchService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class TaskSearchCommand extends Command {

    private final IUserService iUserService;
    private final SearchService searchService;

    @Override
    public String getName() {
        return "task-search";
    }

    @Override
    public String getDescription() {
        return "Find desired task by name or description";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        if (iUserService.getCurrentUser() != null) {
            try {
                System.out.println("Enter task to find");
                String name = reader.readLine();
                searchService.taskSearch(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}

