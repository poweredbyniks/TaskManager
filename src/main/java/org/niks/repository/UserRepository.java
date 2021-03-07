package org.niks.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import org.niks.enums.AccessRoles;
import org.niks.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public final class UserRepository implements IUserRepository {

    private final HikariDataSource dataSource;
    private final Logger logger = LoggerFactory.getLogger(ProjectRepository.class);

    public UserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NotNull
    public List<User> findAll() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement statement = dataSource.getConnection().createStatement();
            String SQL = ("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User(
                        AccessRoles.valueOf(resultSet.getString("accessRoles")),
                        resultSet.getLong("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("passwordHash")
                );
                list.add(user);
            }
        } catch (SQLException throwables) {
            logger.atError().log("FindAll exception (User repo)", new Exception(throwables));
        }
        return list;
    }

    @NotNull
    public Optional<User> findOne(@NotNull final String name) {
        User user = null;
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("SELECT * FROM users WHERE userName = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(
                    AccessRoles.valueOf(resultSet.getString("accessRoles")),
                    resultSet.getLong("userID"),
                    resultSet.getString("userName"),
                    resultSet.getString("passwordHash")
            );
        } catch (SQLException throwables) {
            logger.atError().log("FindOne exception (User repo)", new Exception(throwables));
        }
        return Optional.ofNullable(user);
    }

    public void save(@NotNull final User user) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
            statement.setString(1, String.valueOf(user.getAccessRoles()));
            statement.setLong(2, user.getUserID());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPasswordHash());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.atError().log("Save exception (User repo)", new Exception(throwables));
        }
    }

    public void passwordUpdate(@NotNull final String password, final long userID) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("UPDATE users SET passwordHash = ? WHERE userID = ?");
            statement.setString(1, password);
            statement.setLong(2, userID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.atError().log("PasswordUpdate exception (User repo)", new Exception(throwables));
        }
    }

    public void remove(@NotNull final String name) {
        try {
            PreparedStatement statement =
                    dataSource.getConnection().prepareStatement("DELETE FROM users WHERE projectName = ?");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.atError().log("Remove exception (User repo)", new Exception(throwables));
        }
    }
}