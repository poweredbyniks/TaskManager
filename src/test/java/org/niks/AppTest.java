package org.niks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

class AppTest {

    @Test
    public void testHelp() {
        String helpCommand = "help\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(helpCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.HELP_OUTPUT, os.toString(), "Incorrect help output");
    }

    @Test
    public void testProjectList() {
        String projectListCommand = "user-login\nnewUser\n123\nproject-list\ncreation date\n exit\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT, os.toString(), "Incorrect project-list ordered by creation date output");
    }

    @Test
    public void testProjectListOrderedByStartDate() {
        String projectListCommand = "user-login\nnewUser\n123\nproject-list\nstart date\n exit\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT_ORDERED_BY_START_DATE, os.toString(), "Incorrect project-list output ordered by start date");
    }

    @Test
    public void testProjectListOrderedByFinishDate() {
        String projectListCommand = "user-login\nnewUser\n123\nproject-list\nfinish date\n exit\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT_ORDERED_BY_FINISH_DATE, os.toString(), "Incorrect project-list output ordered by start date");
    }

    @Test
    public void testProjectListOrderedByStatus() {
        String projectListCommand = "user-login\nnewUser\n123\nproject-list\nstatus\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT_ORDERED_BY_STATUS, os.toString(), "Incorrect project-list output ordered by status");
    }


    @Test
    public void testProjectListWithTask() {
        String projectListCommand = "user-login\nnewUser\n123\nproject-list\ncreation date\nsave\nexit\n \u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_WITH_TASK_OUTPUT, os.toString(), "Incorrect project-list with task output");
    }

    @Test
    public void testTaskList() {
        String projectListCommand = "user-reg\nnewUser\n123\ntask-list\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_LIST_OUTPUT, os.toString(), "Incorrect task-list output");
    }

    @Test
    public void testProjectIsolation() {
        String projectListCommand = "user-login\nnewUser\n123\nuser-login\nsecondUser\n321\nproject-list\n\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_ISOLATION_OUTPUT, os.toString(), "Incorrect project user isolation output");
    }

    @Test
    public void testUserEditCommand() {
        String testUserEditCommand = "user-login\nnewUser\n123\nuser-edit\nLarry Page\n";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.USER_NAME_EDIT_COMMAND, os.toString(), "Incorrect user-edit output");
    }

    @Test
    public void testProjectSearch() {
        String testUserEditCommand = "user-login\nnewUser\n123\nproject-search\nsec\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_SEARCH_OUTPUT, os.toString(), "Incorrect project search output");
    }

    @Test
    public void testTaskSearch() {
        String testUserEditCommand = "user-login\nnewUser\n123\ntask-search\nsec\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_SEARCH_OUTPUT, os.toString(), "Incorrect task search output");
    }

    @Test
    public void tempTest(){
        String testUserEditCommand = "user-login\nnewUser\n123\nchange-status-p\nd\n\n\u001a";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_SEARCH_OUTPUT, os.toString(), "Incorrect task search output");
    }
}



