package org.niks.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @With
    Status taskStatus;
    Date creationDate;

    @JsonCreator
    public Task(@JsonProperty("taskID") long taskID,
                @JsonProperty("taskName") String taskName,
                @JsonProperty("projectName") String projectName,
                @JsonProperty("taskDescription") String taskDescription,
                @JsonProperty("startDate") Date startDate,
                @JsonProperty("finishDate") Date finishDate,
                @JsonProperty("userID") long userID,
                @JsonProperty("taskStatus") Status taskStatus,
                @JsonProperty("creationDate") Date creationDate) {
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
