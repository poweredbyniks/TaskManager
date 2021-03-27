package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.exception.RepositoryException;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Slf4j
@Repository
public final class TaskRepository implements ITaskRepository {

    private final IUserService userService;
<<<<<<< HEAD

    private final Map<String, Task> taskMap = readJSON().stream().collect(Collectors.toMap(Task::getTaskName, task -> task));

    public TaskRepository(IUserService userService) {
        this.userService = userService;
=======
    private final HikariDataSource dataSource;

    @Autowired
    public TaskRepository(IUserService userService, HikariDataSource dataSource) {
        this.userService = userService;
        this.dataSource = dataSource;
>>>>>>> dev
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Task> findAll() {
        ArrayList<Task> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
                statement.setLong(1, currentUser().getUserID());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(taskExtraction(resultSet));
                    }
                }
            }
        } catch (SQLException throwables) {
            log.atError().log("FindAll exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("FindAll", this.getClass().getSimpleName(), throwables);
        }
        return list;
    }

    @NotNull
    public List<Task> findAllTasks(final long projectID) {
        ArrayList<Task> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_TASKS_BY_PROJECT_ID_SQL)) {
                statement.setLong(1, currentUser().getUserID());
                statement.setLong(2, projectID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(taskExtraction(resultSet));
                    }
                }
            }
        } catch (SQLException throwables) {
            log.atError().log("FindAllTasksInProject exception " +
                    this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("FindAllTasksInProject", this.getClass().getSimpleName(), throwables);
        }
        return list;
    }

    @NotNull
    public Optional<Task> findOne(@NotNull final String name) {
        Task task;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(FIND_ONE_SQL)) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    task = taskExtraction(resultSet);
                }
            }
        } catch (SQLException throwables) {
            log.atError().log("FindOne exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("FindOne", this.getClass().getSimpleName(), throwables);
        }
        return Optional.of(task);
    }

    @NotNull
    public List<Task> taskSearch(final @NotNull String word) {
        ArrayList<Task> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(TASK_SEARCH_SQL)) {
                statement.setString(1, word);
                statement.setString(2, word);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(taskExtraction(resultSet));
                    }
                }
            }
        } catch (SQLException throwables) {
            log.atError().log("TaskSearch exception " +
                    this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("TaskSearch", this.getClass().getSimpleName(), throwables);
        }
        return list;
    }

    @NotNull
    private Task taskExtraction(@NotNull final ResultSet resultSet) throws SQLException {
        Task task = new Task(
                resultSet.getLong("taskID"),
                resultSet.getLong("userID"),
                resultSet.getLong("projectID"),
                resultSet.getString("taskName"),
                resultSet.getString("projectName"),
                resultSet.getString("projectDescription"),
                resultSet.getDate("startDate"),
                resultSet.getDate("finishDate"),
                Status.valueOf(resultSet.getString("taskStatus")),
                resultSet.getDate("creationDate")
        );
        task = Optional.of(task).get();
        return task;
    }

    public void save(@NotNull final Task task) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
                statement.setLong(1, task.getTaskID());
                statement.setLong(2, task.getUserID());
                statement.setLong(3, task.getProjectID());
                statement.setString(4, task.getTaskName());
                statement.setString(5, task.getProjectName());
                statement.setString(6, task.getTaskDescription());
                statement.setDate(7, (Date) task.getStartDate());
                statement.setDate(8, (Date) task.getFinishDate());
                statement.setString(9, task.getTaskStatus().toString());
                statement.setDate(10, (Date) task.getCreationDate());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            log.atError().log("Save exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("Save ", this.getClass().getSimpleName(), throwables);
        }
    }

    public void update(@NotNull final Task task) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                statement.setString(1, task.getTaskName());
                statement.setString(2, task.getProjectName());
                statement.setString(3, task.getTaskDescription());
                statement.setDate(4, (Date) task.getStartDate());
                statement.setDate(5, (Date) task.getFinishDate());
                statement.setString(6, String.valueOf(task.getTaskStatus()));
                statement.setDate(7, (Date) task.getCreationDate());
                statement.setLong(8, task.getTaskID());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            log.atError().log("Update exception " + this.getClass().getSimpleName(), new Exception(throwables));
        }
    }

    public void remove(@NotNull final String name) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(REMOVE_SQL)) {
                statement.setString(1, name);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            log.atError().log("Remove exception " + this.getClass().getSimpleName(), new Exception(throwables));
        }
    }

    public void removeAll() {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(REMOVE_ALL_SQL)) {
                statement.setLong(1, currentUser().getUserID());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            log.atError().log("RemoveAll exception " + this.getClass().getSimpleName(), new Exception(throwables));
        }
    }

    static final String FIND_ALL_SQL = "SELECT * FROM projects WHERE userID = ?";
    static final String FIND_ALL_TASKS_BY_PROJECT_ID_SQL = "SELECT * FROM projects WHERE userID = ? AND projectID = ?";
    static final String FIND_ONE_SQL = "SELECT * FROM task WHERE taskName = ?";
    static final String SAVE_SQL = "INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    static final String UPDATE_SQL = "UPDATE tasks SET taskName = ?, projectName = ?" +
            "taskDescription = ?, startDate = ?, finishDate = ?, status = ?, creationDate = ? " +
            "WHERE taskID = ?";
    static final String REMOVE_SQL = "DELETE FROM tasks WHERE taskName = ?";
    static final String REMOVE_ALL_SQL = "DELETE FROM tasks WHERE userID = ?";
    final static String TASK_SEARCH_SQL = "SELECT * FROM tasks " +
            "WHERE taskName LIKE '%?%' OR taskDescription LIKE '%?%'";
}
