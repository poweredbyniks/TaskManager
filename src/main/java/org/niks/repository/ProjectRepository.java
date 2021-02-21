package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.User;
import org.niks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public final class ProjectRepository extends Serialization<Project> implements IProjectRepository {

    private final IUserService userService;

    private static final String URL = "jdbc:postgresql://localhost:5432/task_manager_DB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "niks";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Autowired
    public ProjectRepository(IUserService userService) {
        this.userService = userService;
    }

    @NotNull
    public List<Project> findAll() {
        ArrayList<Project> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = String.format("SELECT * FROM projects WHERE userID = %s",
                    userService.getCurrentUser().getUserID());
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getInt("projectID"),
                        resultSet.getInt("userID"),
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
                    connection.prepareStatement("SELECT FROM projects WHERE projectName LIKE ?");
            ResultSet resultSet = statement.executeQuery();
            statement.setString(1, name);
            resultSet.next();
            project = new Project(
                    resultSet.getInt("projectID"),
                    resultSet.getInt("userID"),
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
                    connection.prepareStatement("INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
                    connection.prepareStatement(
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
                    connection.prepareStatement("DELETE FROM projects WHERE projectName LIKE ?");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String SQL = String.format("DELETE FROM projects WHERE userID = %s",
                    userService.getCurrentUser().getUserID());
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Project> readJSON() {
        List<Project> list = new ArrayList<>();
        try {
            final ObjectMapper mapper = new ObjectMapper();
            list = Arrays.asList(mapper.readValue(new File(FilePath.PROJECT_FILE_PATH), Project[].class));
        } catch (IOException e) {
            System.out.println("No project data found");
        }
        return list;
    }

//    public void serialize() throws IOException {
//        writeJSON(projectMap, FilePath.PROJECT_FILE_PATH);
//    }
}
