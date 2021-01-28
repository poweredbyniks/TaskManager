package org.niks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.FilePath;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestOutputs {
    public static String readProjectJSON() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder s = new StringBuilder();
        try {
            final ObjectMapper projectMapper = new ObjectMapper();
            Project[] projectsArray = projectMapper.readValue(new File(FilePath.PROJECT_FILE_PATH), Project[].class);
            @NotNull final ObjectMapper taskMapper = new ObjectMapper();
            Task[] tasksArray = taskMapper.readValue(new File(FilePath.TASK_FILE_PATH), Task[].class);
            List<Project> projectList = new ArrayList<>();


            for (Project project : projectsArray) {
                if (project.getUserID() == 4) {
                    projectList.add(project);
                }
            }
            List<Task> taskList = new ArrayList<>();
            for (Project project : projectList) {
                s.append("\n\nPROJECT name: ")
                        .append(project.getProjectName())
                        .append("\nStatus: ")
                        .append(project.getProjectStatus().getStatus())
                        .append("\nDescription: ")
                        .append(project.getProjectDescription())
                        .append("\nStart date: ")
                        .append(dateFormat.format(project.getStartDate()))
                        .append("\nFinish date: ")
                        .append(dateFormat.format(project.getFinishDate()));

                taskList = Arrays.stream(tasksArray)
                        .filter(task -> task.getProjectID() == project.getProjectID())
                        .collect(Collectors.toList());

                if (taskList.size() != 0) {
                    taskList.forEach((task -> s.append("\nTasks:" + "\nTASK name: ")
                            .append(task.getTaskName())
                            .append("\nTask status: ")
                            .append(task.getTaskStatus().getStatus())
                            .append("\nTask description: ")
                            .append(task.getTaskDescription())
                            .append("\nStart date: ")
                            .append(dateFormat.format(task.getStartDate()))
                            .append("\nFinish date: ")
                            .append(dateFormat.format(task.getFinishDate()))));
                } else {
                    s.append("\nTask list is empty");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }

    public static String s = readProjectJSON();

    public static final String HELP_OUTPUT =
            "Welcome to the Task Manager.\n" +
                    "Sign up, please\nType help for instructions\n" +
                    "login : User authorization\n" +
                    "reg-u : Registration of a new user\n" +
                    "help : Help command\n" +
                    "create-p : Creation of a project\n" +
                    "list-p : List of the existing projects\n" +
                    "remove-p : Removes a project\n" +
                    "clear-p : Removes all projects\n" +
                    "create-t : Creation of a task\n" +
                    "list-t : List of the existing tasks\n" +
                    "remove-t : Remove a task\n" +
                    "clear-t : Removes all tasks\n" +
                    "edit-u : Edit user name\n" +
                    "log out : Log out\n" +
                    "info-u : User info: user ID, user name\n" +
                    "password-edit-u : Edit current password\n" +
                    "search-p : Find desired project by name or description\n" +
                    "search-t : Find desired task by name or description\n" +
                    "save : Save created projects and tasks\n" +
                    "change-status-p : Change project status\n" +
                    "change-status-t : Change task status\n";

    public static final String PROJECT_LIST_OUTPUT =
            "Welcome to the Task Manager.\n" +
                    "Sign up, please\n" +
                    "Type help for instructions\n" +
                    "Enter user name\n" +
                    "Enter password\n" +
                    "Welcome test\n" +
                    "Order by:\n" +
                    "creation date\n" +
                    "start date\n" +
                    "finish date\n" +
                    "status\n" +
                    "Ordered by creation date" + s + "\n";
//                    "PROJECT name: cat\n" +
//                    "Status: planned\n" +
//                    "Description: lovely\n" +
//                    "Start date: 26.01.2021\n" +
//                    "Finish date: 27.01.2021\n" +
//                    "Tasks:\n" +
//                    "TASK name: water\n" +
//                    "Task status: working\n" +
//                    "Task description: \n" +
//                    "Start date: 27.01.2021\n" +
//                    "Finish date: 27.01.2021\n" +
//                    "\n" +
//                    "PROJECT name: coffee\n" +
//                    "Status: planned\n" +
//                    "Description: latte\n" +
//                    "Start date: 27.03.2021\n" +
//                    "Finish date: 28.03.2021\n" +
//                    "Task list is empty\n" +
//                    "\n" +
//                    "PROJECT name: buy milk\n" +
//                    "Status: planned\n" +
//                    "Description: \n" +
//                    "Start date: 19.01.2021\n" +
//                    "Finish date: 23.01.2021\n" +
//                    "Task list is empty\n" +
//                    "\n" +
//                    "PROJECT name: buy new mac\n" +
//                    "Status: planned\n" +
//                    "Description: \n" +
//                    "Start date: 29.03.2021\n" +
//                    "Finish date: 29.03.2021\n" +
//                    "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_START_DATE =
            "Welcome to the Task Manager.\n" +
                    "Sign up, please\n" +
                    "Type help for instructions\n" +
                    "Enter user name\n" +
                    "Enter password\n" +
                    "Welcome test\n" +
                    "Order by:\n" +
                    "creation date\n" +
                    "start date\n" +
                    "finish date\n" +
                    "status\n" +
                    "Ordered by start date\n" +
                    "\n" +
                    "PROJECT name: buy milk\n" +
                    "Status: planned\n" +
                    "Description: \n" +
                    "Start date: 19.01.2021\n" +
                    "Finish date: 23.01.2021\n" +
                    "Task list is empty\n" +
                    "\n" +
                    "PROJECT name: cat\n" +
                    "Status: planned\n" +
                    "Description: lovely\n" +
                    "Start date: 26.01.2021\n" +
                    "Finish date: 27.01.2021\n" +
                    "Tasks:\n" +
                    "TASK name: water\n" +
                    "Task status: working\n" +
                    "Task description: \n" +
                    "Start date: 27.01.2021\n" +
                    "Finish date: 27.01.2021\n" +
                    "\n" +
                    "PROJECT name: coffee\n" +
                    "Status: planned\n" +
                    "Description: latte\n" +
                    "Start date: 27.03.2021\n" +
                    "Finish date: 28.03.2021\n" +
                    "Task list is empty\n" +
                    "\n" +
                    "PROJECT name: buy new mac\n" +
                    "Status: planned\n" +
                    "Description: \n" +
                    "Start date: 29.03.2021\n" +
                    "Finish date: 29.03.2021\n" +
                    "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_FINISH_DATE = "Welcome to the Task Manager.\n" +
            "Sign up, please\n" +
            "Type help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome test\n" +
            "Order by:\n" +
            "creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by finish date\n" +
            "\n" +
            "PROJECT name: buy milk\n" +
            "Status: planned\n" +
            "Description: \n" +
            "Start date: 19.01.2021\n" +
            "Finish date: 23.01.2021\n" +
            "Task list is empty\n" +
            "\n" +
            "PROJECT name: cat\n" +
            "Status: planned\n" +
            "Description: lovely\n" +
            "Start date: 26.01.2021\n" +
            "Finish date: 27.01.2021\n" +
            "Tasks:\n" +
            "TASK name: water\n" +
            "Task status: working\n" +
            "Task description: \n" +
            "Start date: 27.01.2021\n" +
            "Finish date: 27.01.2021\n" +
            "\n" +
            "PROJECT name: coffee\n" +
            "Status: planned\n" +
            "Description: latte\n" +
            "Start date: 27.03.2021\n" +
            "Finish date: 28.03.2021\n" +
            "Task list is empty\n" +
            "\n" +
            "PROJECT name: buy new mac\n" +
            "Status: planned\n" +
            "Description: \n" +
            "Start date: 29.03.2021\n" +
            "Finish date: 29.03.2021\n" +
            "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_STATUS = "Welcome to the Task Manager.\n" +
            "Sign up, please\n" +
            "Type help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome test\n" +
            "Order by:\n" +
            "creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by status\n" +
            "\n" +
            "PROJECT name: cat\n" +
            "Status: planned\n" +
            "Description: lovely\n" +
            "Start date: 26.01.2021\n" +
            "Finish date: 27.01.2021\n" +
            "Tasks:\n" +
            "TASK name: water\n" +
            "Task status: working\n" +
            "Task description: \n" +
            "Start date: 27.01.2021\n" +
            "Finish date: 27.01.2021\n" +
            "\n" +
            "PROJECT name: coffee\n" +
            "Status: planned\n" +
            "Description: latte\n" +
            "Start date: 27.03.2021\n" +
            "Finish date: 28.03.2021\n" +
            "Task list is empty\n" +
            "\n" +
            "PROJECT name: buy milk\n" +
            "Status: planned\n" +
            "Description: \n" +
            "Start date: 19.01.2021\n" +
            "Finish date: 23.01.2021\n" +
            "Task list is empty\n" +
            "\n" +
            "PROJECT name: buy new mac\n" +
            "Status: planned\n" +
            "Description: \n" +
            "Start date: 29.03.2021\n" +
            "Finish date: 29.03.2021\n" +
            "Task list is empty\n";

    public static final String TASK_LIST_OUTPUT = "Welcome to the Task Manager.\n" +
            "Sign up, please\n" +
            "Type help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome test\n" +
            "Order by:\n" +
            "creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by creation date\n" +
            "\n" +
            "TASK water in the project cat\n" +
            "Task status: working\n" +
            "Start date: 27.01.2021\n" +
            "Finish date: 27.01.2021\n";

    public static final String PROJECT_ISOLATION_OUTPUT = "Welcome to the Task Manager.\n" +
            "Sign up, please\n" +
            "Type help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome test\n" +
            "Order by:\n" +
            "creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by creation date\n" +
            "\n" +
            "PROJECT name: cat\n" +
            "Status: planned\n" +
            "Description: lovely\n" +
            "Start date: 26.01.2021\n" +
            "Finish date: 27.01.2021\n" +
            "Tasks:\n" +
            "TASK name: water\n" +
            "Task status: working\n" +
            "Task description: \n" +
            "Start date: 27.01.2021\n" +
            "Finish date: 27.01.2021\n" +
            "\n" +
            "PROJECT name: coffee\n" +
            "Status: planned\n" +
            "Description: latte\n" +
            "Start date: 27.03.2021\n" +
            "Finish date: 28.03.2021\n" +
            "Task list is empty\n" +
            "\n" +
            "PROJECT name: buy milk\n" +
            "Status: planned\n" +
            "Description: \n" +
            "Start date: 19.01.2021\n" +
            "Finish date: 23.01.2021\n" +
            "Task list is empty\n" +
            "\n" +
            "PROJECT name: buy new mac\n" +
            "Status: planned\n" +
            "Description: \n" +
            "Start date: 29.03.2021\n" +
            "Finish date: 29.03.2021\n" +
            "Task list is empty\n";

    public static final String USER_NAME_EDIT_COMMAND = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "User newUser created\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "Enter new user name\n" +
            "Your new user name is Larry Page\n";

    public static final String USER_PASSWORD_EDIT_COMMAND = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "User newUser created\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "Enter new password\n" +
            "Password updated\n";

    public static final String PROJECT_SEARCH_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "User newUser created\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "Enter project name\n" +
            "Enter project description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Project newProject created\n" +

            "Enter project name\n" +
            "Enter project description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Project secondProject created\n" +

            "Enter project name\n" +
            "Enter project description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Project thirdProject created\n" +
            "Enter project to find\n" +
            "secondProject\n";

    public static final String TASK_SEARCH_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "User newUser created\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "Enter project name\n" +
            "Enter project description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Project newProject created\n" +

            "Enter project to include to\n" +
            "Enter task name\n" +
            "Enter task description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Task Task created and added to the project newProject\n" +

            "Enter project to include to\n" +
            "Enter task name\n" +
            "Enter task description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Task secondTask created and added to the project newProject\n" +

            "Enter project to include to\n" +
            "Enter task name\n" +
            "Enter task description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Task thirdTask created and added to the project newProject\n" +
            "Enter task to find\n" +
            "secondTask\n";
}
