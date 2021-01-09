package org.niks.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
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
}
