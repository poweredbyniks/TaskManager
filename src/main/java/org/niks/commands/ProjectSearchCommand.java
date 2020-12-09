package org.niks.commands;

import lombok.AllArgsConstructor;
import org.niks.service.IUserService;
import org.niks.service.SearchService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class ProjectSearchCommand extends Command {
    IUserService iUserService;
    SearchService searchService;

    @Override
    public String getName() {
        return "project-search";
    }

    @Override
    public String getDescription() {
        return "Find desired project by name or description";
    }

    @Override
    public void execute(BufferedReader reader) throws IOException {
        if (iUserService.getCurrentUser() != null) {
            try {
                System.out.println("Enter project to find");
                String name = reader.readLine();
                searchService.projectSearch(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
