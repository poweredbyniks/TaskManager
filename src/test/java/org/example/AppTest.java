package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;



class AppTest {

    @Test
    public void testMain() throws IOException {
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
}

