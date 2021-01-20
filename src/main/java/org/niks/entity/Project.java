package org.niks.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @With
    Status projectStatus;
    Date creationDate;

    @JsonCreator
    public Project(@JsonProperty("projectID") long projectID,
                   @JsonProperty("projectName") String projectName,
                   @JsonProperty("projectDescription") String projectDescription,
                   @JsonProperty("startDate") Date startDate,
                   @JsonProperty("finishDate") Date finishDate,
                   @JsonProperty("taskList") List<Task> taskList,
                   @JsonProperty("userID") long userID,
                   @JsonProperty("projectStatus") Status projectStatus,
                   @JsonProperty("creationDate") Date creationDate) {
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
