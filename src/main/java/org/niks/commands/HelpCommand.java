package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.Map;

@AllArgsConstructor
public final class HelpCommand extends CommandWithoutUserCheck {
    private final Map<String, Command> commandMap;

    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Help command";
    }

    public void execute(@NotNull final BufferedReader reader) {
        for (Map.Entry<String, Command> commandMap : commandMap.entrySet()) {
            System.out.println(commandMap.getKey() + " : " + commandMap.getValue().getDescription());
        }
    }
}


