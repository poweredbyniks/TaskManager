package org.niks.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.niks.AccessRoles;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class User {
    AccessRoles accessRoles;
    long userID;
    String userName;
    String passwordHash;
}
