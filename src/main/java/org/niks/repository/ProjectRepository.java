package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.User;
import org.niks.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public final class ProjectRepository implements IProjectRepository {

    private final IUserService userService;
    private final HikariDataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger(ProjectRepository.class);

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
        try {
            PreparedStatement statement
                    = dataSource.getConnection().prepareStatement("SELECT * FROM projects WHERE userID = ?");
            statement.setLong(1, currentUser().getUserID());
            ResultSet resultSet = statement.executeQuery();
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
        } catch (SQLException throwables) {
            logger.atError().log("FindAll exception (Project repo)", new Exception(throwables));
        }
        return list;
    }

    @NotNull
    public Optional<Project> findOne(@NotNull final String name) {
        Project project = null;
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("SELECT * FROM projects WHERE projectName = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(project);
    }

    public void save(@NotNull final Project project) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
            throwables.printStackTrace();
        }
    }

    public void update(@NotNull final Project project) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement(
                            "UPDATE projects SET projectName = ?, projectDescription = ?, " +
                                    "startDate = ?, finishDate = ?, status = ?, creationDate = ? WHERE projectID = ?");
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, String.valueOf(project.getStartDate()));
            statement.setString(4, String.valueOf(project.getFinishDate()));
            statement.setString(5, String.valueOf(project.getProjectStatus()));
            statement.setString(6, String.valueOf(project.getCreationDate()));
            statement.setLong(7, project.getProjectID());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(@NotNull final String name) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("DELETE FROM projects WHERE projectName = ?");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeAll() {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("DELETE FROM projects WHERE userID = ?");
            statement.setLong(1, currentUser().getUserID());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
