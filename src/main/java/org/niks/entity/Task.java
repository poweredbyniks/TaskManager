package org.niks.entity;

import lombok.Value;

import java.util.Date;


public class Task {
    long taskID;
    String taskName;
    String projectName;
    String taskDescription;
    Date startDate;
    Date finishDate;
    long userID;
    Status taskStatus;
    Date creationDate;

    public Task(){

    }

    public long getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public long getUserID() {
        return userID;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Task(long taskID, String taskName, String projectName, String taskDescription, Date startDate,
                Date finishDate, long userID, Status taskStatus, Date creationDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.userID = userID;
        this.taskStatus = taskStatus;
        this.creationDate = creationDate;
    }
}
