package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.Status;
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
public final class ProjectRepository implements IProjectRepository {

    private final IUserService userService;
    private final HikariDataSource dataSource;

    @Autowired
    public ProjectRepository(IUserService userService, HikariDataSource dataSource) {
        this.userService = userService;
        this.dataSource = dataSource;
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Project> findAll() {
        ArrayList<Project> list = new ArrayList<>();
        final String SQL = "SELECT * FROM projects WHERE userID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement
                     = connection.prepareStatement(SQL)) {
            statement.setLong(1, currentUser().getUserID());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project(
                            resultSet.getLong("projectID"),
                            resultSet.getLong("userID"),
                            resultSet.getString("projectName"),
                            resultSet.getString("projectDescription"),
                            resultSet.getDate("startDate"),
                            resultSet.getDate("finishDate"),
                            Status.valueOf(resultSet.getString("projectStatus")),
                            resultSet.getDate("creationDate")
                    );
                    list.add(project);
                }
            }
        } catch (SQLException throwables) {
            log.error("FindAll exception (Project repo)", throwables);
            throw new RepositoryException("FindAll", this.getClass().getSimpleName(), throwables);
        }
        return list;
    }

    @NotNull
    public Optional<Project> findOne(@NotNull final String name) {
        Project project;
        final String SQL = "SELECT * FROM projects WHERE projectName = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                project = new Project(
                        resultSet.getLong("projectID"),
                        resultSet.getLong("userID"),
                        resultSet.getString("projectName"),
                        resultSet.getString("projectDescription"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("finishDate"),
                        Status.valueOf(resultSet.getString("projectStatus")),
                        resultSet.getDate("creationDate")
                );
            }
        } catch (SQLException throwables) {
            log.error("FindOne exception (Project repo)", throwables);
            throw new RepositoryException("FindOne", this.getClass().getName(), throwables);
        }
        return Optional.of(project);
    }

    public void save(@NotNull final Project project) {
        final String SQL = "INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL)) {
            statement.setLong(1, project.getProjectID());
            statement.setLong(2, project.getUserID());
            statement.setString(3, project.getProjectName());
            statement.setString(4, project.getProjectDescription());
            statement.setDate(5, (Date) project.getStartDate());
            statement.setDate(6, (Date) project.getFinishDate());
            statement.setString(7, project.getProjectStatus().toString());
            statement.setDate(8, (Date) project.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("Save exception (Project repo)", throwables);
            throw new RepositoryException("Save", this.getClass().getName(), throwables);
        }
    }

    public void update(@NotNull final Project project) {
        final String SQL = "UPDATE projects SET projectName = ?, projectDescription = ?, startDate = ?, " +
                "finishDate = ?, status = ?, creationDate = ? WHERE projectID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL)) {
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, String.valueOf(project.getStartDate()));
            statement.setString(4, String.valueOf(project.getFinishDate()));
            statement.setString(5, String.valueOf(project.getProjectStatus()));
            statement.setString(6, String.valueOf(project.getCreationDate()));
            statement.setLong(7, project.getProjectID());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("Update exception (Project repo)", throwables);
            throw new RepositoryException("Update", this.getClass().getName(), throwables);
        }
    }

    public void remove(@NotNull final String name) {
        final String SQL = "DELETE FROM projects WHERE projectName = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("Remove exception (Project repo)", throwables);
            throw new RepositoryException("Remove", this.getClass().getName(), throwables);
        }
    }

    public void removeAll() {
        final String SQL = "DELETE FROM projects WHERE userID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL)) {
            statement.setLong(1, currentUser().getUserID());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.error("RemoveAll exception (Project repo)", new Exception(throwables));
            throw new RepositoryException("RemoveAll", this.getClass().getName(), throwables);
        }
    }
}
