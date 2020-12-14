package org.niks;

public class TestOutputs {
    public static final String HELP_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
            "user-login : User authorization\n" +
            "user-reg : Registration of a new user\n" +
            "help : Help command\n" +
            "project-create : Creation of a project\n" +
            "project-list : List of the existing projects\n" +
            "project-remove : Removes a project\n" +
            "project-clear : Removes all projects\n" +
            "task-create : Creation of a task\n" +
            "task-list : List of the existing tasks\n" +
            "task-remove : Removes a task\n" +
            "task-clear : Removes all tasks\n" +
            "user-edit : Edit user name\n" +
            "user-exit : Log out\n" +
            "user-info : User info: user ID, user name\n" +
            "password-edit : Edit current password\n" +
            "project-search : Find desired project by name or description\n" +
            "task-search : Find desired task by name or description\n";


    public static final String PROJECT_CREATE_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Project newProject created\n";

    public static final String PROJECT_LIST_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by creation date\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_START_DATE = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Project goProject created\n" +
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by start date\n" +
            "Project Name: goProject\n" +
            "Description: GoProject\n" +
            "Start date: 30.08.2020\n" +
            "Finish date: 01.10.2020\n" +
            "Task list is empty\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_FINISH_DATE = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Project goProject created\n" +
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by finish date\n" +
            "Project Name: goProject\n" +
            "Description: GoProject\n" +
            "Start date: 30.08.2020\n" +
            "Finish date: 01.10.2020\n" +
            "Task list is empty\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_STATUS = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Project goProject created\n" +
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by status\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Task list is empty\n" +
            "Project Name: goProject\n" +
            "Description: GoProject\n" +
            "Start date: 30.08.2020\n" +
            "Finish date: 01.10.2020\n" +
            "Task list is empty\n";

    public static final String PROJECT_LIST_WITH_TASK_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Task newTask created and added to the project newProject\n" +
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by creation date\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Tasks:\n" +
            "Task name: newTask\n" +
            "Task description: feed a cat\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n";


    public static final String TASK_CREATE_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Task newTask created and added to the project newProject\n";

    public static final String TASK_LIST_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "Task newTask created and added to the project newProject\n" +
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by creation date\n" +
            "Task newTask in the project newProject\n" +
            "Start date: 30.11.2020\n" +
            "Finish date: 01.12.2020\n";

    public static final String PROJECT_ISOLATION_OUTPUT = "Welcome to the Task Manager.\nSign up, please\nType help for instructions\n" +
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
            "newUser logged out\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "User secondUser created\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome secondUser\n" +
            "Enter project name\n" +
            "Enter project description\n" +
            "Enter starting date dd.MM.yyyy\n" +
            "Enter finishing date dd.MM.yyyy\n" +
            "Project secondProject created\n" +
            "Order by creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by creation date\n" +
            "Project Name: secondProject\n" +
            "Description: Project description\n" +
            "Start date: 29.12.2020\n" +
            "Finish date: 30.12.2020\n" +
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
