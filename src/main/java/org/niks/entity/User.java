package org.niks.entity;

import lombok.Value;
import org.niks.AccessRoles;

@Value
public class User {
    AccessRoles accessRoles;
    long userID;
    String userName;
    String passwordHash;
}