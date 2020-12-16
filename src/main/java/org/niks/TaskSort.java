package org.niks;

import org.niks.entity.Task;

import java.util.Comparator;

public enum TaskSort {
    START_DATE((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate())),
    FINISH_DATE((o1, o2) -> o1.getFinishDate().compareTo(o2.getFinishDate())),
    STATUS((o1, o2) -> o1.getTaskStatus().compareTo(o2.getTaskStatus()));

    public Comparator<Task> taskComparator;

    TaskSort(Comparator<Task> taskComparator) {
        this.taskComparator = taskComparator;
    }

}
