package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.niks.enums.AccessRoles;
import org.niks.entity.User;
import org.niks.exception.RepositoryException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Slf4j
@Repository
public final class UserRepository implements IUserRepository {

    private final HikariDataSource dataSource;

    public UserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NotNull
    public List<User> findAll() {
        ArrayList<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery((FIND_ALL_SQL))) {
                while (resultSet.next()) {
                    list.add(userExtraction(resultSet));
                }
            }
        } catch (SQLException throwables) {
            log.atError().log("FindAll exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("FindAll", this.getClass().getSimpleName(), throwables);
        }
        return list;
    }

    @NotNull
    public Optional<User> findByID(final long userID) {
        User user;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = userExtraction(resultSet);
            }
        } catch (SQLException throwables) {
            log.atError().log("FindOne exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("FindByID", this.getClass().getSimpleName(), throwables);
        }
        return Optional.of(user);
    }

    private User userExtraction(ResultSet resultSet) throws SQLException {
        User user = new User(
                AccessRoles.valueOf(resultSet.getString("accessRoles")),
                resultSet.getLong("userID"),
                resultSet.getString("userName"),
                resultSet.getString("passwordHash")
        );
        user = Optional.of(user).get();
        return user;
    }

    @NotNull
    public Optional<User> findOne(@NotNull final String name) {
        User user;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_ONE_SQL)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user = new User(
                        AccessRoles.valueOf(resultSet.getString("accessRoles")),
                        resultSet.getLong("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("passwordHash")
                );
            }
        } catch (SQLException throwables) {
            log.atError().log("FindOne exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("FindOne", this.getClass().getSimpleName(), throwables);
        }
        return Optional.of(user);
    }

    public void save(@NotNull final User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(SAVE_SQL);
            statement.setString(1, String.valueOf(user.getAccessRoles()));
            statement.setLong(2, user.getUserID());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPasswordHash());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.atError().log("Save exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("Save", this.getClass().getSimpleName(), throwables);
        }
    }

    public void passwordUpdate(@NotNull final String password, final long userID) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(PASSWORD_UPDATE_SQL)) {

            statement.setString(1, password);
            statement.setLong(2, userID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.atError().log("PasswordUpdate exception" + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("PasswordUpdate", this.getClass().getSimpleName(), throwables);
        }
    }

    public void remove(@NotNull final String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_SQL)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            log.atError().log("Remove exception " + this.getClass().getSimpleName(), new Exception(throwables));
            throw new RepositoryException("Remove", this.getClass().getSimpleName(), throwables);
        }
    }

    static final String FIND_ALL_SQL = "SELECT * FROM users";
    static final String FIND_BY_ID_SQL = "SELECT * FROM users WHERE userID = ?";
    static final String FIND_ONE_SQL = "SELECT * FROM users WHERE userName = ?";
    static final String SAVE_SQL = "INSERT INTO users VALUES (?, ?, ?, ?)";
    static final String PASSWORD_UPDATE_SQL = "UPDATE users SET passwordHash = ? WHERE userID = ?";
    static final String REMOVE_SQL = "DELETE FROM users WHERE projectName = ?";

}