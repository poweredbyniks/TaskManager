package org.niks;

public class TestOutputs {
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
                    "Welcome newUser\n" +
                    "Order by:\n" +
                    "creation date\n" +
                    "start date\n" +
                    "finish date\n" +
                    "status\n" +
                    "Ordered by creation date\n" +
                    "Project Name: secondProject\n" +
                    "Status: PLANNED\n" +
                    "Description: secondDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Task list is empty\n" +
                    "Project Name: thirdProject\n" +
                    "Status: PLANNED\n" +
                    "Description: thirdDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Task list is empty\n" +
                    "Project Name: newProject\n" +
                    "Status: PLANNED\n" +
                    "Description: Project description\n" +
                    "Start date: 30.10.2020\n" +
                    "Finish date: 31.10.2020\n" +
                    "Tasks:\n" +
                    "Task name: Task\n" +
                    "Task status: PLANNED\n" +
                    "Task description: Description\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Tasks:\n" +
                    "Task name: secondTask\n" +
                    "Task status: PLANNED\n" +
                    "Task description: secondDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Tasks:\n" +
                    "Task name: thirdTask\n" +
                    "Task status: PLANNED\n" +
                    "Task description: thirdDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_START_DATE =
            "Welcome to the Task Manager.\n" +
                    "Sign up, please\n" +
                    "Type help for instructions\n" +
                    "Enter user name\n" +
                    "Enter password\n" +
                    "Welcome newUser\n" +
                    "Order by:\n" +
                    "creation date\n" +
                    "start date\n" +
                    "finish date\n" +
                    "status\n" +
                    "Ordered by finish date\n" +
                    "Project Name: newProject\n" +
                    "Status: PLANNED\n" +
                    "Description: Project description\n" +
                    "Start date: 30.10.2020\n" +
                    "Finish date: 31.10.2020\n" +
                    "Tasks:\n" +
                    "Task name: Task\n" +
                    "Task status: PLANNED\n" +
                    "Task description: Description\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Tasks:\n" +
                    "Task name: secondTask\n" +
                    "Task status: PLANNED\n" +
                    "Task description: secondDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Tasks:\n" +
                    "Task name: thirdTask\n" +
                    "Task status: PLANNED\n" +
                    "Task description: thirdDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Project Name: secondProject\n" +
                    "Status: PLANNED\n" +
                    "Description: secondDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Task list is empty\n" +
                    "Project Name: thirdProject\n" +
                    "Status: PLANNED\n" +
                    "Description: thirdDescription\n" +
                    "Start date: 25.11.2020\n" +
                    "Finish date: 26.11.2020\n" +
                    "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_FINISH_DATE = "Welcome to the Task Manager.\n" +
            "Sign up, please\n" +
            "Type help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "Order by:\n" +
            "creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by finish date\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Tasks:\n" +
            "Task name: Task\n" +
            "Task description: Description\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Tasks:\n" +
            "Task name: secondTask\n" +
            "Task description: secondDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Tasks:\n" +
            "Task name: thirdTask\n" +
            "Task description: thirdDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Project Name: secondProject\n" +
            "Description: secondDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Task list is empty\n" +
            "Project Name: thirdProject\n" +
            "Description: thirdDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Task list is empty\n";

    public static final String PROJECT_LIST_OUTPUT_ORDERED_BY_STATUS = "Welcome to the Task Manager.\n" +
            "Sign up, please\n" +
            "Type help for instructions\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "Order by:\n" +
            "creation date\n" +
            "start date\n" +
            "finish date\n" +
            "status\n" +
            "Ordered by status\n" +
            "Project Name: newProject\n" +
            "Description: Project description\n" +
            "Start date: 30.10.2020\n" +
            "Finish date: 31.10.2020\n" +
            "Tasks:\n" +
            "Task name: Task\n" +
            "Task description: Description\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Tasks:\n" +
            "Task name: secondTask\n" +
            "Task description: secondDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Tasks:\n" +
            "Task name: thirdTask\n" +
            "Task description: thirdDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Project Name: secondProject\n" +
            "Description: secondDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
            "Task list is empty\n" +
            "Project Name: thirdProject\n" +
            "Description: thirdDescription\n" +
            "Start date: 25.11.2020\n" +
            "Finish date: 26.11.2020\n" +
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
            "Order by:\n" +
            "creation date\n" +
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
            "Order by:\n" +
            "creation date\n" +
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
            "Order by:\n" +
            "creation date\n" +
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
