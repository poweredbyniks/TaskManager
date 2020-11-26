package org.niks.entity;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class Project {
    long projectID;
    String projectName;
    String projectDescription;
    Date startDate;
    Date finishDate;
    List<Task> taskList;
    long userID;

}
