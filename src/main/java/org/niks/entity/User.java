package org.niks.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.niks.AccessRoles;

@Value
public class User {
    AccessRoles accessRoles;
    long userID;
    String userName;
    String passwordHash;

    @JsonCreator
    public User(@JsonProperty("accessRoles") AccessRoles accessRoles,
                @JsonProperty("userID") long userID,
                @JsonProperty("userName") String userName,
                @JsonProperty("passwordHash") String passwordHash) {
        this.accessRoles = accessRoles;
        this.userID = userID;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }
}
