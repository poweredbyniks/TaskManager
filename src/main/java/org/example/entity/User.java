package org.example.entity;

import lombok.Value;
import org.example.AccessRoles;

@Value
public class User {
    private AccessRoles accessRoles;
    private long userID;
    private String userName;
    private String md5Password;

    public AccessRoles getAccessRoles() {
        return accessRoles.USER;
    }
}