package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.TaskSort;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.repository.IProjectRepository;
import org.niks.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class TaskService implements ITaskService {

    private final IUserService userService;
    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;

    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public TaskService(IUserService userService, ITaskRepository taskRepository, IProjectRepository projectRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public void create(@NotNull final String taskName, @NotNull final String projectName,
                       @NotNull final String taskDescription, @NotNull final String startDate,
                       @NotNull final String finishDate) {

        final User currentUser = userService.getCurrentUser();

        try {
            long projectID = 0;
            if (projectRepository.findOne(projectName).isPresent()) {
                projectID = projectRepository.findOne(projectName).get().getProjectID();
            }

            Task task = new Task(
                    currentUser.getUserID(),
                    randomNumber(),
                    projectID,
                    taskName,
                    projectName,
                    taskDescription,
                    dateFormat.parse(startDate),
                    dateFormat.parse(finishDate),
                    Status.PLANNED,
                    new Date());

            taskRepository.save(task);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public @NotNull List<Task> list() {
        return taskList();
    }

    public @NotNull List<Task> list(@NotNull final String order) {
        final List<Task> taskList = taskList();
        taskList.sort(TaskSort.valueOf(order).getTaskComparator());
        return taskList;
    }

    public @NotNull List<Task> list(@NotNull final TaskSort order) {
        final List<Task> taskList = taskList();
        taskList.sort(order.getTaskComparator());
        return taskList;
    }

    public void remove(@NotNull final String taskToRemove) {
        taskRepository.remove(taskToRemove);
    }

    public void clear() {
        taskRepository.removeAll();
    }

    @NotNull
    public List<Task> taskSearch(@NotNull final String source) {
        final List<Task> taskList = taskList();
        return taskList
                .stream()
                .filter(task -> task.getTaskName().toLowerCase().contains(source.toLowerCase()) ||
                        task.getTaskDescription().toLowerCase().contains(source.toLowerCase()))
                .collect(Collectors.toList());
    }

    @NotNull
    public List<Task> taskList() {
        return taskRepository.findAll();
    }

    public Task findExactMatch(@NotNull final String name) {
        Task task = null;
        if (taskRepository.findOne(name).isPresent()) {
            task = taskRepository.findOne(name).get();
        }
        return task;
    }

    @NotNull
    public List<Project> projectList() {
        return projectRepository.findAll();
    }

    public void update(@NotNull final Project project) {

    }

    public void serialize() throws IOException {
        taskRepository.serialize();
    }

    public static long randomNumber() {
        final SecureRandom random = new SecureRandom();
        return (random.nextInt(Integer.MAX_VALUE));
    }
}
