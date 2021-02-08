package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public final class TaskRepository extends Serialization<Task> implements ITaskRepository {

    private final IUserService userService;
    private final Map<String, Task> taskMap = readJSON().stream().collect(Collectors.toMap(Task::getTaskName, task -> task));

    @Autowired
    public TaskRepository(IUserService userService) {
        this.userService = userService;

    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Task> findAll() {
        return taskMap.values().stream()
                .filter(task -> task.getUserID() == currentUser().getUserID())
                .collect(Collectors.toList());
    }

    @NotNull
    public Optional<Task> findOne(@NotNull final String name) {
        return Optional.ofNullable(taskMap.get(name));
    }

    public boolean save(@NotNull final Task task) {
        taskMap.put(task.getTaskName(), task);
        return true;
    }

    public boolean update(@NotNull final Task task) {
        return false;
    }

    public void remove(@NotNull final String name) {
        if (findOne(name).isPresent()) {
            if (taskMap.get(name).getUserID() == currentUser().getUserID()) {
                taskMap.remove(name);
            }
        }
    }

    public void removeAll() {
        taskMap.entrySet().removeIf(stringTaskEntry ->
                stringTaskEntry.getValue().getUserID() == currentUser().getUserID());
    }

    @Override
    public List<Task> readJSON() {
        List<Task> list = new ArrayList<>();
        try {
            @NotNull final ObjectMapper mapper = new ObjectMapper();
            list = Arrays.asList(mapper.readValue(new File(FilePath.TASK_FILE_PATH), Task[].class));
        } catch (IOException e) {
            System.out.println("No task data found");
        }
        return list;
    }

    public void serialize() throws IOException {
        writeJSON(taskMap, FilePath.TASK_FILE_PATH);
    }
}
