package org.niks;

import org.niks.commands.*;
import org.niks.repository.ProjectRepository;
import org.niks.repository.TaskRepository;
import org.niks.repository.UserRepository;
import org.niks.service.ProjectService;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap {
    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);
    ProjectRepository projectRepository = new ProjectRepository(userService);
    TaskRepository taskRepository = new TaskRepository(userService, projectRepository);
    ProjectService projectService = new ProjectService(projectRepository);
    TaskService taskService = new TaskService(taskRepository, projectRepository);

    public Map<String, Command> commandMap = new LinkedHashMap<>();
    HelpCommand helpCommand = new HelpCommand(commandMap);
    ProjectClearCommand projectClearCommand = new ProjectClearCommand(userService, projectService);
    ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(userService, projectService);
    ProjectListCommand projectListCommand = new ProjectListCommand(userService, projectService);
    ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(userService, projectService);
    ProjectSearchCommand projectSearchCommand = new ProjectSearchCommand(userService, projectService);

    TaskClearCommand taskClearCommand = new TaskClearCommand(userService, taskService);
    TaskCreateCommand taskCreateCommand = new TaskCreateCommand(userService, taskService);
    TaskListCommand taskListCommand = new TaskListCommand(userService, taskService);
    TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(userService, taskService);
    TaskSearchCommand taskSearchCommand = new TaskSearchCommand(userService, taskService);

    UserEndSessionCommand userEndSessionCommand = new UserEndSessionCommand(userService);
    UserAuthorizationCommand userAuthorizationCommand = new UserAuthorizationCommand(userService, userEndSessionCommand);
    UserEditCommand userEditCommand = new UserEditCommand(userService);
    UserInfoCommand userInfoCommand = new UserInfoCommand(userService);
    UserPasswordEditCommand userPasswordUpdateCommand = new UserPasswordEditCommand(userService);
    UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand(userService);

    public Bootstrap() throws IOException {
    }

    public void init() {
        commandMap.put(userAuthorizationCommand.getName(), userAuthorizationCommand);
        commandMap.put(userRegistrationCommand.getName(), userRegistrationCommand);
        commandMap.put(helpCommand.getName(), helpCommand);
        commandMap.put(projectCreateCommand.getName(), projectCreateCommand);
        commandMap.put(projectListCommand.getName(), projectListCommand);
        commandMap.put(projectRemoveCommand.getName(), projectRemoveCommand);
        commandMap.put(projectClearCommand.getName(), projectClearCommand);
        commandMap.put(taskCreateCommand.getName(), taskCreateCommand);
        commandMap.put(taskListCommand.getName(), taskListCommand);
        commandMap.put(taskRemoveCommand.getName(), taskRemoveCommand);
        commandMap.put(taskClearCommand.getName(), taskClearCommand);
        commandMap.put(userEditCommand.getName(), userEditCommand);
        commandMap.put(userEndSessionCommand.getName(), userEndSessionCommand);
        commandMap.put(userInfoCommand.getName(), userInfoCommand);
        commandMap.put(userPasswordUpdateCommand.getName(), userPasswordUpdateCommand);
        commandMap.put(projectSearchCommand.getName(), projectSearchCommand);
        commandMap.put(taskSearchCommand.getName(), taskSearchCommand);
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
                    projectRepository.readJSON();
                    taskRepository.readJSON();
                    break;
                }
                input = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
