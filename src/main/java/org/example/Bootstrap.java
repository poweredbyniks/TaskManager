package org.example;

import org.example.commands.*;
import org.example.entity.User;
import org.example.repository.ProjectRepo;
import org.example.repository.TaskRepo;
import org.example.repository.UserRepo;
import org.example.service.ProjectService;
import org.example.service.TaskService;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap {

    ProjectRepo projectRepo = new ProjectRepo();
    TaskRepo taskRepo = new TaskRepo();
    UserRepo userRepo = new UserRepo();
    ProjectService projectService = new ProjectService(projectRepo);
    TaskService taskService = new TaskService(taskRepo, projectRepo);
    UserService userService = new UserService(userRepo);
    public static Map<String, Command> commandMap = new LinkedHashMap<>();

    HelpCommand helpCommand = new HelpCommand();
    ProjectClearCommand projectClearCommand = new ProjectClearCommand(projectService);
    ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(projectService);
    ProjectListCommand projectListCommand = new ProjectListCommand(projectService);
    ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(projectService);

    TaskClearCommand taskClearCommand = new TaskClearCommand(taskService);
    TaskCreateCommand taskCreateCommand = new TaskCreateCommand(taskService, projectRepo);
    TaskListCommand taskListCommand = new TaskListCommand(taskService);
    TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(taskService);

    UserAuthorizationCommand userAuthorizationCommand = new UserAuthorizationCommand(userService);
    UserEditCommand userEditCommand = new UserEditCommand(userService);
    UserEndSessionCommand userEndSessionCommand = new UserEndSessionCommand(userService);
    UserInfoCommand userInfoCommand = new UserInfoCommand(userService);
    UserPasswordUpdateCommand userPasswordUpdateCommand = new UserPasswordUpdateCommand(userService);
    UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand(userService);

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
        User user1 = new User(AccessRoles.USER, 21, "1", "123");

        System.out.println("Welcome to the Task Manager.\nType help to get instructions");
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
