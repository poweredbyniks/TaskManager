package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class AppTest {

    @Test
    public void testMainAddition() {

        Map<String, Project> projectMapExpected = new HashMap<>();
        Project project1 = new Project("name1");
        Project project2 = new Project("name2");
        projectMapExpected.put("name1", project1);
        projectMapExpected.put("name2", project2);
        Map<String, Project> projectMapActual = new HashMap<>();
        projectMapActual.put("name1", project1);
        projectMapActual.put("name2", project2);
        Assert.assertEquals(projectMapExpected, projectMapActual);

        Map<String, Task> taskMapExpected = new HashMap<>();
        Task task1 = new Task("name1");
        Task task2 = new Task("name2");
        taskMapExpected.put("name1", task1);
        taskMapExpected.put("name2", task2);
        Map<String, Task> taskMapActual = new HashMap<>();
        taskMapActual.put("name1", task1);
        taskMapActual.put("name2", task2);
        Assert.assertEquals(taskMapExpected, taskMapActual);
    }
}
