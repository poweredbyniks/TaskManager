package org.example.entity;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class Project {
    private long projectID;
    private String projectName;
    private String projectDescription;
    private Date startDate;
    private Date finishDate;
    private List<Task> taskArray;

}
