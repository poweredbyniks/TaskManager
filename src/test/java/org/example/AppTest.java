package org.example;


import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;


class AppTest {

    @Test
    public void testHelp() throws IOException {
        String helpCommand = "help\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(helpCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assert.assertEquals("Incorrect help output", HELP_OUTPUT, os.toString());
    }

    public static final String HELP_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions\n" +
            "project-create : Creation of a project\n" +
            "help : Help command\n" +
            "task-list : List of the existing tasks\n" +
            "task-clear : Removes all tasks\n" +
            "task-create : Creation of a task\n" +
            "project-list : List of the existing projects\n" +
            "project-remove : Removes a project\n" +
            "project-clear : Removes all projects\n" +
            "task-remove : Removes a task\n";

    @Test
    public void testProjectCreate() throws IOException {
        String projectCreateCommand = "project-create\nnewProject\nProject description" +
                "\n30.10.2020\n31.10.2020 exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectCreateCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assert.assertEquals("Incorrect project-create output", PROJECT_CREATE_OUTPUT, os.toString());
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
        Assert.assertEquals("Incorrect project-list output", PROJECT_LIST_OUTPUT, os.toString());
    }

    public static final String PROJECT_LIST_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions" +
            "\n[Enter project name]\n[Enter project description]\n[Enter starting date dd.MM.yyyy]" +
            "\n[Enter finishing date dd.MM.yyyy]\n[Project newProject created]\nProject Name: newProject\n" +
            "Description: Project description\nStart date: 30.10.2020\nFinish date: 31.10.2020\nTasks: []\n";

    @Test
    public void testTaskCreate() throws IOException {
        String projectListCommand = "project-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n31.11.2020 exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assert.assertEquals("Incorrect task-create output", TASK_CREATE_OUTPUT, os.toString());
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
                "\n30.11.2020\n31.11.2020\ntask-list\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assert.assertEquals("Incorrect task-list output", TASK_LIST_OUTPUT, os.toString());
    }

    public static final String TASK_LIST_OUTPUT = "Welcome to the Task Manager.\nType help to get instructions" +
            "\n[Enter project name]\n[Enter project description]\n[Enter starting date dd.MM.yyyy]" +
            "\n[Enter finishing date dd.MM.yyyy]\n[Project newProject created]\n" +
            "[Enter project to include to]\n[Enter task name]\n[Enter task description]" +
            "\n[Enter starting date dd.MM.yyyy]\n[Enter finishing date dd.MM.yyyy]" +
            "\n[Task newTask created and added to the project newProject]\n[Task newTask in the project newProject]\n";

}


