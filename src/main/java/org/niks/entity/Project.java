package org.niks.entity;

import lombok.Value;

import java.util.Date;
import java.util.List;

public class Project {
    long projectID;
    String projectName;
    String projectDescription;
    Date startDate;
    Date finishDate;
    List<Task> taskList;
    long userID;
    Status projectStatus;
    Date creationDate;

    public Project(){

    }
    public long getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public long getUserID() {
        return userID;
    }

    public Status getProjectStatus() {
        return projectStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Project(long projectID, String projectName, String projectDescription, Date startDate, Date finishDate,
                   List<Task> taskList, long userID, Status projectStatus, Date creationDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.taskList = taskList;
        this.userID = userID;
        this.projectStatus = projectStatus;
        this.creationDate = creationDate;
    }
}
