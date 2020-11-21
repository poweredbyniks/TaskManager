package org.niks.entity;

import lombok.Value;

import java.util.Date;

@Value
public class Task {
    private long taskID;
    private String taskName;
    private String projectName;
    private String taskDescription;
    private Date startDate;
    private Date finishDate;
    private long userID;
}
