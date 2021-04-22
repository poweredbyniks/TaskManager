package org.niks.DTO;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.Task;

import java.util.Date;
import java.util.Set;

@Value
@AllArgsConstructor
public class ProjectDto {
    Long userID;
    Long projectID;
    String projectName;
    String projectDescription;
    Date startDate;
    Date finishDate;
    Status projectStatus;
    Date creationDate;
    Set<Task> tasks;

    @NotNull
    public static ProjectDto fromDomain(@NotNull final Project domain) {
        return new ProjectDto(
                domain.getUserID(),
                domain.getProjectID(),
                domain.getProjectName(),
                domain.getProjectDescription(),
                domain.getStartDate(),
                domain.getFinishDate(),
                domain.getProjectStatus(),
                domain.getCreationDate(),
                domain.getTasks());
    }
}
