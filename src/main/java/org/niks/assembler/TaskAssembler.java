package org.niks.assembler;

import org.jetbrains.annotations.NotNull;
import org.niks.DTO.TaskDto;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TaskAssembler {

    public Task fromDto(@NotNull final TaskDto dto) {
        return new Task(
                dto.getTaskID(),
                new User(dto.getUserID()),
                new Project(dto.getUserID(), dto.getProjectID()),
                dto.getTaskName(),
                dto.getTaskDescription(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getTaskStatus(),
                dto.getCreationDate());
    }
}
