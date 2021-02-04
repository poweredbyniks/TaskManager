package org.niks.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Value

public class Project {
    long userID;
    long projectID;
    String projectName;
    String projectDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date finishDate;

    @With
    Status projectStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    Date creationDate;

    @JsonCreator
    public Project(@JsonProperty("userID") long userID,
                   @JsonProperty("projectID") long projectID,
                   @JsonProperty("projectName") String projectName,
                   @JsonProperty("projectDescription") String projectDescription,
                   @JsonProperty("startDate") Date startDate,
                   @JsonProperty("finishDate") Date finishDate,
                   @JsonProperty("projectStatus") Status projectStatus,
                   @JsonProperty("creationDate") Date creationDate) {
        this.userID = userID;
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.projectStatus = projectStatus;
        this.creationDate = creationDate;
    }
}
