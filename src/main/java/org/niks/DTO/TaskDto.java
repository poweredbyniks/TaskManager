package org.niks.DTO;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.niks.entity.User;

import javax.persistence.ManyToOne;
import java.util.Date;

@Value
public class TaskDto {
    @With
    Long taskID;
    @ManyToOne
    User user;
    @ManyToOne
    Project project;
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
                domain.getUser(),
                domain.getProject(),
                domain.getTaskName(),
                domain.getTaskDescription(),
                domain.getStartDate(),
                domain.getFinishDate(),
                domain.getTaskStatus(),
                domain.getCreationDate());
    }
}
