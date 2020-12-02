package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.util.Map;


@Value
public class HelpCommand extends Command {
    Map<String, Command> commandMap;

    @NotNull
    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Help command";
    }

    public void execute(BufferedReader reader) {
        if (UserService.getCurrentUser() != null) {
            for (Map.Entry<String, Command> commandMap : commandMap.entrySet()) {
                System.out.println(commandMap.getKey() + " : " + commandMap.getValue().getDescription());
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}


