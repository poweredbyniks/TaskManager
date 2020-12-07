package org.niks.entity;

import lombok.Value;

import java.util.Date;

@Value
public class Task {
    long taskID;
    String taskName;
    String projectName;
    String taskDescription;
    Date startDate;
    Date finishDate;
    long userID;
    ProgressStatus progressStatus;
    Date creationDate;
}
