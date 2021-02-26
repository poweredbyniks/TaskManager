package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public final class ProjectRepository implements IProjectRepository {

    private final IUserService userService;
    private static final Connection connectionPool = DBConnection.getConnection();

    @Autowired
    public ProjectRepository(IUserService userService) {
        this.userService = userService;
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Project> findAll() {
        ArrayList<Project> list = new ArrayList<>();
        try {
            Statement statement = connectionPool.createStatement();
            String SQL = String.format("SELECT * FROM projects WHERE userID = %s",
                    currentUser().getUserID());
            ResultSet resultSet = statement.executeQuery(SQL);
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
            throwables.printStackTrace();
        }
        return list;
    }

    @NotNull
    public Optional<Project> findOne(@NotNull final String name) {
        Project project = null;
        try {
            PreparedStatement statement =
                    connectionPool.prepareStatement("SELECT * FROM projects WHERE projectName LIKE ?");
            ResultSet resultSet = statement.executeQuery();
            statement.setString(1, name);
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
                    connectionPool.prepareStatement("INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, project.getProjectID());
            statement.setLong(2, project.getUserID());
            statement.setString(3, project.getProjectName());
            statement.setString(4, project.getProjectDescription());
            statement.setDate(5, (Date) project.getStartDate());
            statement.setDate(6, (Date) project.getFinishDate());
            statement.setString(7, project.getProjectStatus().toString()); //override toString() in status?
            statement.setDate(8, (Date) project.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(@NotNull final Project project) {
        try {
            PreparedStatement statement =
                    connectionPool.prepareStatement(
                            "UPDATE projects SET projectName = ?, projectDescription = ?, " +
                                    "startDate = ?, finishDate = ?, status = ?, creationDate = ?");
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, String.valueOf(project.getStartDate()));
            statement.setString(4, String.valueOf(project.getFinishDate()));
            statement.setString(5, String.valueOf(project.getProjectStatus()));
            statement.setString(6, String.valueOf(project.getCreationDate()));
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(@NotNull final String name) {
        try {
            PreparedStatement statement =
                    connectionPool.prepareStatement("DELETE FROM projects WHERE projectName LIKE ?");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeAll() {
        try {
            Statement statement = connectionPool.createStatement();
            String SQL = String.format("DELETE FROM projects WHERE userID = %s",
                    currentUser().getUserID());
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
