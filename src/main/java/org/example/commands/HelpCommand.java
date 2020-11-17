package org.example.commands;

import lombok.Value;
import org.example.Bootstrap;
import org.example.entity.User;

import java.io.BufferedReader;
import java.util.Map;


@Value
public class HelpCommand extends Command {

    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Help command";
    }

    public void execute(BufferedReader reader, User user) {

        if (user != null) {
            for (Map.Entry<String, Command> commandMap : Bootstrap.commandMap.entrySet()) {
                System.out.println(commandMap.getKey() + " : " + commandMap.getValue().getDescription());
            }
        } else {
            System.out.println("Access denied");
        }
    }
}


