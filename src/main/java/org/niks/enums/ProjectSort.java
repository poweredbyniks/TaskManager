package org.niks.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.niks.entity.Project;

import java.util.Comparator;

@AllArgsConstructor
@Getter
public enum ProjectSort {
    CREATION_DATE((o1, o2) -> o1.getCreationDate().compareTo(o2.getCreationDate()), "creation date"),
    START_DATE((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()), "start date"),
    FINISH_DATE((o1, o2) -> o1.getFinishDate().compareTo(o2.getFinishDate()), "finish date"),
    STATUS((o1, o2) -> o1.getProjectStatus().compareTo(o2.getProjectStatus()), "status");

    private final Comparator<Project> projectComparator;
    private final String order;

}
