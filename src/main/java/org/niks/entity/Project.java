package org.niks.entity;

import lombok.*;
import lombok.experimental.NonFinal;

import java.util.Date;
import java.util.List;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Project {
    long projectID;
    String projectName;
    String projectDescription;
    Date startDate;
    Date finishDate;
    List<Task> taskList;
    long userID;
    @NonFinal
    @Setter
    Status projectStatus;
    Date creationDate;
}
