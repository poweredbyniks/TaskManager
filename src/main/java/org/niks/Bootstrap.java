package org.niks;

import org.niks.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap {

    public Map<String, Command> commandMap = new LinkedHashMap<>();

    HelpCommand helpCommand = new HelpCommand(commandMap);
    ProjectClearCommand projectClearCommand = new ProjectClearCommand();
    ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand();
    ProjectListCommand projectListCommand = new ProjectListCommand();
    ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand();

    TaskClearCommand taskClearCommand = new TaskClearCommand();
    TaskCreateCommand taskCreateCommand = new TaskCreateCommand();
    TaskListCommand taskListCommand = new TaskListCommand();
    TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand();

    UserAuthorizationCommand userAuthorizationCommand = new UserAuthorizationCommand();
    UserEditCommand userEditCommand = new UserEditCommand();
    UserEndSessionCommand userEndSessionCommand = new UserEndSessionCommand();
    UserInfoCommand userInfoCommand = new UserInfoCommand();
    UserPasswordEditCommand userPasswordUpdateCommand = new UserPasswordEditCommand();
    UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand();

    public void init() {
        commandMap.put(helpCommand.getName(), helpCommand);
        commandMap.put(projectCreateCommand.getName(), projectCreateCommand);
        commandMap.put(projectListCommand.getName(), projectListCommand);
        commandMap.put(projectRemoveCommand.getName(), projectRemoveCommand);
        commandMap.put(projectClearCommand.getName(), projectClearCommand);
        commandMap.put(taskCreateCommand.getName(), taskCreateCommand);
        commandMap.put(taskListCommand.getName(), taskListCommand);
        commandMap.put(taskRemoveCommand.getName(), taskRemoveCommand);
        commandMap.put(taskClearCommand.getName(), taskClearCommand);
        commandMap.put(userAuthorizationCommand.getName(), userAuthorizationCommand);
        commandMap.put(userEditCommand.getName(), userEditCommand);
        commandMap.put(userEndSessionCommand.getName(), userEndSessionCommand);
        commandMap.put(userInfoCommand.getName(), userInfoCommand);
        commandMap.put(userPasswordUpdateCommand.getName(), userPasswordUpdateCommand);
        commandMap.put(userRegistrationCommand.getName(), userRegistrationCommand);
        commandExecution();
    }

    public void commandExecution() {
        System.out.println("Welcome to the Task Manager.\nSign up, please\nType help for instructions");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (input != null) {
                if (commandMap.containsKey(input)) {
                    commandMap.get(input).execute(reader);
                } else if (input.equals("exit")) {
                    break;
                }
                input = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
