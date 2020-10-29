package org.example;

import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Value
public class Project {
    private UUID projectID;
    private String projectName;
    private String projectDescription;
    private Date startDate;
    private Date finishDate;
    public ArrayList<Task> taskArray;

}
