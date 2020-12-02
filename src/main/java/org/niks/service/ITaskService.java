package org.niks.service;

import org.niks.entity.Task;

public interface ITaskService {
    void create(Task task);

    void list();

    void remove(String taskToRemove);

    void clear();
}
