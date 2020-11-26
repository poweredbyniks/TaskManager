package org.niks.commands;

import lombok.Value;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.util.Map;


@Value
public class HelpCommand extends Command {
    private UserService userService;
    private Map<String, Command> commandMap;

    public HelpCommand(Map<String, Command> commandMap, UserService userService) {
        this.userService = userService;
        this.commandMap = commandMap;
    }

    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Help command";
    }

    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            for (Map.Entry<String, Command> commandMap : commandMap.entrySet()) {
                System.out.println(commandMap.getKey() + " : " + commandMap.getValue().getDescription());
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}


