package org.niks.assembler;

import org.jetbrains.annotations.NotNull;
import org.niks.DTO.ProjectDto;
import org.niks.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectAssembler {

    public Project fromDto(@NotNull final ProjectDto dto) {
        return new Project(
                dto.getUserID(),
                dto.getProjectID(),
                dto.getProjectName(),
                dto.getProjectDescription(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getProjectStatus(),
                dto.getCreationDate(),
                dto.getTasks());
    }
}
