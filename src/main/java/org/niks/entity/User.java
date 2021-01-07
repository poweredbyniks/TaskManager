package org.niks.entity;

import lombok.Value;
import org.niks.AccessRoles;

public class User {
    AccessRoles accessRoles;
    long userID;
    String userName;
    String passwordHash;

    public AccessRoles getAccessRoles() {
        return accessRoles;
    }

    public long getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public User(AccessRoles accessRoles, long userID, String userName, String passwordHash) {
        this.accessRoles = accessRoles;
        this.userID = userID;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public User() {
    }
}
