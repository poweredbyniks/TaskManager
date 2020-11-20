package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;


class AppTest {

    @Test
    public void testHelp() throws IOException {
        String helpCommand = "newUser\n123\nnewUser\n123\nhelp\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(helpCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(HELP_OUTPUT, os.toString(), "Incorrect help output");
    }

    public static final String HELP_OUTPUT = "Welcome to the Task Manager.\nSign up, please\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "[User newUser created]\n" +
            "Enter user name\n" +
            "Enter password\n" +
            "Welcome newUser\n" +
            "help : Help command\n" +
            "project-create : Creation of a project\n" +
            "project-list : List of the existing projects\n" +
            "project-remove : Removes a project\n" +
            "project-clear : Removes all projects\n" +
            "task-create : Creation of a task\n" +
            "task-list : List of the existing tasks\n" +
            "task-remove : Removes a task\n" +
            "task-clear : Removes all tasks\n" +
            "user-login : User authorization\n" +
            "user-edit : Edit user name\n" +
            "user-exit : Log out\n" +
            "user-info : User info: user ID, user name\n" +
            "password-update : Current password update\n" +
            "user-reg : Registration of a new user\n";


    @Test
    public void testProjectCreate() throws IOException {
        String projectCreateCommand = "project-create\nnewProject\nProject description" +
                "\n30.10.2020\n31.10.2020\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectCreateCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(PROJECT_CREATE_OUTPUT, os.toString(), "Incorrect project-create output");
    }

    public static final String PROJECT_CREATE_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions" +
            "\n[Enter project name]\n[Enter project description]\n[Enter starting date dd.MM.yyyy]" +
            "\n[Enter finishing date dd.MM.yyyy]\n[Project newProject created]\n";


    @Test
    public void testProjectList() throws IOException {
        String projectListCommand = "project-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-list";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(PROJECT_LIST_OUTPUT, os.toString(), "Incorrect project-list output");
    }

    public static final String PROJECT_LIST_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions" +
            "\n[Enter project name]\n[Enter project description]\n[Enter starting date dd.MM.yyyy]" +
            "\n[Enter finishing date dd.MM.yyyy]\n[Project newProject created]\nProject Name: newProject\n" +
            "Description: Project description\nStart date: 30.10.2020\nFinish date: 31.10.2020\nTasks: []\n";

    @Test
    public void testTaskCreate() throws IOException {
        String projectListCommand = "project-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n31.11.2020 \nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TASK_CREATE_OUTPUT, os.toString(), "Incorrect task-create output");
    }

    public static final String TASK_CREATE_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions" +
            "\n[Enter project name]\n[Enter project description]" +
            "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]" +
            "\n[Project newProject created]\n[Enter project to include to]\n[Enter task name]" +
            "\n[Enter task description]\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]" +
            "\n[Task newTask created and added to the project newProject]\n";


    @Test
    public void testTaskList() throws IOException {
        String projectListCommand = "project-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n01.12.2020\ntask-list\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TASK_LIST_OUTPUT, os.toString(), "Incorrect task-create output");
    }

    public static final String TASK_LIST_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions" +
            "\n[Enter project name]\n[Enter project description]\n[Enter starting date dd.MM.yyyy]" +
            "\n[Enter finishing date dd.MM.yyyy]\n[Project newProject created]\n" +
            "[Enter project to include to]\n[Enter task name]\n[Enter task description]" +
            "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]" +
            "\n[Task newTask created and added to the project newProject]\n[Task newTask in the project newProject]" +
            "\nStart date: 30.11.2020\nFinish date: 01.12.2020\n";

}


