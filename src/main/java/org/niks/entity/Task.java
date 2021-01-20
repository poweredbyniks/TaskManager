package org.niks.entity;

import lombok.*;
import lombok.experimental.NonFinal;

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
    @NonFinal
    @Setter
    Status taskStatus;
    Date creationDate;
}
