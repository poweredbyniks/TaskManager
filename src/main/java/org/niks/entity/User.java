package org.niks.entity;

import lombok.Value;
import org.niks.AccessRoles;

@Value
public class User {
    private AccessRoles accessRoles;
    private long userID;
    private String userName;
    private String hashPassword;
}