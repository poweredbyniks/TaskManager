package org.niks.assembler;

import org.jetbrains.annotations.NotNull;
import org.niks.DTO.TaskDto;
import org.niks.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskAssembler {

    public Task fromDto(@NotNull final TaskDto dto) {
        return new Task(
                dto.getTaskID(),
                dto.getUser(),
                dto.getProject(),
                dto.getTaskName(),
                dto.getTaskDescription(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getTaskStatus(),
                dto.getCreationDate());
    }
}
