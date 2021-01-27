package org.niks;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
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
    private final UserRepository userRepository = new UserRepository();
    private final UserService userService = new UserService(userRepository);
    private final ProjectRepository projectRepository = new ProjectRepository(userService);
    private final TaskRepository taskRepository = new TaskRepository(userService);
    private final ProjectService projectService = new ProjectService(projectRepository);
    private final TaskService taskService = new TaskService(taskRepository, projectRepository);

    private final Map<String, Command> commandMap = new LinkedHashMap<>();
    private final HelpCommand helpCommand = new HelpCommand(commandMap);
    private final ProjectClearCommand projectClearCommand = new ProjectClearCommand(userService, projectService);
    private final ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(userService, projectService);
    private final ProjectListCommand projectListCommand = new ProjectListCommand(userService, projectService, taskService);
    private final ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(userService, projectService);
    private final ProjectSearchCommand projectSearchCommand = new ProjectSearchCommand(userService, projectService);

    private final TaskClearCommand taskClearCommand = new TaskClearCommand(userService, taskService);
    private final TaskCreateCommand taskCreateCommand = new TaskCreateCommand(userService, taskService);
    private final TaskListCommand taskListCommand = new TaskListCommand(userService, taskService);
    private final TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(userService, taskService);
    private final TaskSearchCommand taskSearchCommand = new TaskSearchCommand(userService, taskService);
    private final TaskStatusChangeCommand taskStatusChangeCommand = new TaskStatusChangeCommand(userService, taskService);
    private final ProjectTaskSaveCommand projectTaskSaveCommand = new ProjectTaskSaveCommand(userService, projectService, taskService);
    private final ProjectStatusChangeCommand projectStatusChangeCommand = new ProjectStatusChangeCommand(userService, projectService);

    private final UserEndSessionCommand userEndSessionCommand = new UserEndSessionCommand(userService);
    private final UserAuthorizationCommand userAuthorizationCommand = new UserAuthorizationCommand(userService, userEndSessionCommand);
    private final UserEditCommand userEditCommand = new UserEditCommand(userService);
    private final UserInfoCommand userInfoCommand = new UserInfoCommand(userService);
    private final UserPasswordEditCommand userPasswordUpdateCommand = new UserPasswordEditCommand(userService);
    private final UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand(userService);


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
        commandMap.put(projectTaskSaveCommand.getName(), projectTaskSaveCommand);
        commandMap.put(projectStatusChangeCommand.getName(), projectStatusChangeCommand);
        commandMap.put(taskStatusChangeCommand.getName(), taskStatusChangeCommand);
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
                    try {
                        projectService.serialize();
                        taskService.serialize();
                    } catch (MismatchedInputException e) {
                        System.out.println("No data found");
                    }
                    break;
                }
                input = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
