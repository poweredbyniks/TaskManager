package org.niks;


import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals(TestOutputs.HELP_OUTPUT, os.toString(), "Incorrect help output");
    }

    @Test
    public void testProjectCreate() throws IOException {
        String projectCreateCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\n" +
                "Project description\n30.10.2020\n31.10.2020\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectCreateCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_CREATE_OUTPUT, os.toString(), "Incorrect project-create output");
    }

    @Test
    public void testProjectList() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-list\ncreation date\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT, os.toString(), "Incorrect project-list ordered by creation date output");
    }

    @Test
    public void testProjectListOrderedByStartDate() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-create\ngoProject\nGoProject\n30.08.2020\n31.09.2020\nproject-list\nstart date\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT_ORDERED_BY_START_DATE, os.toString(), "Incorrect project-list output ordered by start date");
    }

    @Test
    public void testProjectListOrderedByFinishDate() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-create\ngoProject\nGoProject\n30.08.2020\n31.09.2020\nproject-list\nfinish date\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT_ORDERED_BY_FINISH_DATE, os.toString(), "Incorrect project-list output ordered by start date");
    }

    @Test
    public void testProjectListOrderedByStatus() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-create\ngoProject\nGoProject\n30.08.2020\n31.09.2020\nproject-list\nstatus\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT_ORDERED_BY_STATUS, os.toString(), "Incorrect project-list output ordered by status");
    }


    @Test
    public void testProjectListWithTask() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nfeed a cat\n25.11.2020\n26.11.2020\nproject-list\ncreation date\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_WITH_TASK_OUTPUT, os.toString(), "Incorrect project-list with task output");
    }

    @Test
    public void testTaskCreate() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n31.11.2020 \nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_CREATE_OUTPUT, os.toString(), "Incorrect task-create output");
    }

    @Test
    public void testTaskList() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n31.11.2020\ntask-list\n\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_LIST_OUTPUT, os.toString(), "Incorrect task-list output");
    }

    @Test
    public void testProjectIsolation() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nuser-reg\nsecondUser\n321\nuser-login\nsecondUser\n321\nproject-create\nsecondProject\nProject description\n" +
                "29.12.2020\n30.12.2020\nproject-list\n\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_ISOLATION_OUTPUT, os.toString(), "Incorrect project user isolation output");
    }

    @Test
    public void testUserEditCommand() throws IOException {
        String testUserEditCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nuser-edit\nLarry Page\n";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.USER_NAME_EDIT_COMMAND, os.toString(), "Incorrect user-edit output");
    }

    @Test
    public void testUserPasswordEditCommand() throws IOException {
        String testUserEditCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\npassword-edit\n321\n";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.USER_PASSWORD_EDIT_COMMAND, os.toString(), "Incorrect password-edit output");
    }

    @Test
    public void testProjectSearch() throws IOException {
        String testUserEditCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-create\nsecondProject\nsecondDescription\n25.11.2020\n26.11.2020\nproject-create\nthirdProject\nthirdDescription\n25.11.2020\n26.11.2020" +
                "\nproject-search\nsec\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_SEARCH_OUTPUT, os.toString(), "Incorrect project search output");
    }

    @Test
    public void testTaskSearch() throws IOException {
        String testUserEditCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nTask\nDescription\n25.11.2020\n26.11.2020\ntask-create\nnewProject\nsecondTask\nsecondDescription\n" +
                "25.11.2020\n26.11.2020\ntask-create\nnewProject\nthirdTask\nthirdDescription\n25.11.2020\n26.11.2020\ntask-search\nsec\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(testUserEditCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_SEARCH_OUTPUT, os.toString(), "Incorrect task search output");
    }
}



