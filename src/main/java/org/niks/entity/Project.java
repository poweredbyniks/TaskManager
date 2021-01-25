package org.niks.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@Value
public class Project {
    long projectID;
    String projectName;
    String projectDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date finishDate;
    long userID;
    @With
    Status projectStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date creationDate;

    @JsonCreator
    public Project(@JsonProperty("projectID") long projectID,
                   @JsonProperty("projectName") String projectName,
                   @JsonProperty("projectDescription") String projectDescription,
                   @JsonProperty("startDate") Date startDate,
                   @JsonProperty("finishDate") Date finishDate,
                   @JsonProperty("userID") long userID,
                   @JsonProperty("projectStatus") Status projectStatus,
                   @JsonProperty("creationDate") Date creationDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.userID = userID;
        this.projectStatus = projectStatus;
        this.creationDate = creationDate;
    }
}
