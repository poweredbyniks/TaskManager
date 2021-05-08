package org.niks.DTO;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Status;
import org.niks.entity.Task;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TaskDto {
    @With
    Long taskID;
    Long userID;
    Long projectID;
    String taskName;
    String taskDescription;
    Date startDate;
    Date finishDate;
    Status taskStatus;
    Date creationDate;

    @NotNull
    public static TaskDto fromDomain(@NotNull final Task domain) {
        return new TaskDto(
                domain.getTaskID(),
                domain.getUser().getUserID(),
                domain.getProject().getProjectID(),
                domain.getTaskName(),
                domain.getTaskDescription(),
                domain.getStartDate(),
                domain.getFinishDate(),
                domain.getTaskStatus(),
                domain.getCreationDate());
    }
}
