package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public final class TaskRepository implements ITaskRepository {

    private final IUserService userService;
    private final HikariDataSource dataSource;

    @Autowired
    public TaskRepository(IUserService userService, HikariDataSource dataSource) {
        this.userService = userService;
        this.dataSource = dataSource;
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Task> findAll() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            String SQL = String.format("SELECT * FROM projects WHERE userID = %s",
                    currentUser().getUserID());
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
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
                list.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @NotNull
    public Optional<Task> findOne(@NotNull final String name) {
        Task task = null;
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM projects WHERE userID = %s");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            task = new Task(
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(task);
    }

    public void save(@NotNull final Task task) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, task.getTaskID());
            statement.setLong(2, task.getUserID());
            statement.setLong(3, task.getProjectID());
            statement.setString(4, task.getTaskName());
            statement.setString(5, task.getProjectName());
            statement.setString(6, task.getTaskDescription());
            statement.setDate(7, (Date) task.getStartDate());
            statement.setDate(8, (Date) task.getFinishDate());
            statement.setString(9, task.getTaskStatus().toString()); //override toString() in status?
            statement.setDate(10, (Date) task.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void update(@NotNull final Task task) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("UPDATE tasks SET taskName = ?, projectName = ?" +
                            "taskDescription = ?, startDate = ?, finishDate = ?, status = ?, creationDate = ?");
            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getProjectName());
            statement.setString(3, task.getTaskDescription());
            statement.setDate(4, (Date) task.getStartDate());
            statement.setDate(5, (Date) task.getFinishDate());
            statement.setString(6, String.valueOf(task.getTaskStatus()));
            statement.setDate(7, (Date) task.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(@NotNull final String name) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("DELETE FROM tasks WHERE taskName LIKE ?");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeAll() {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            String SQL = String.format("DELETE FROM tasks WHERE userID = %s",
                    currentUser().getUserID());
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
