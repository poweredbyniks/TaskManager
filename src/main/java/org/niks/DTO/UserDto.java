package org.niks.DTO;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.niks.enums.AccessRoles;

@Value
@AllArgsConstructor
public class UserDto {
    Long userID;
    AccessRoles accessRoles;
    String userName;
    String passwordHash;

    @NotNull
    public static UserDto fromDomain(@NotNull final User domain) {
        return new UserDto(
                domain.getUserID(),
                domain.getAccessRoles(),
                domain.getUserName(),
                domain.getPasswordHash());
    }
}
