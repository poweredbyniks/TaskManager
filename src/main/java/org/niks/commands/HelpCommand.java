package org.niks.commands;

import lombok.Value;
import org.niks.Bootstrap;
import org.niks.entity.User;

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
            System.out.println("Log in before working");
        }
    }
}


