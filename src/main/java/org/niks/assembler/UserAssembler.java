package org.niks.assembler;

import org.jetbrains.annotations.NotNull;
import org.niks.DTO.UserDto;
import org.niks.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public User fromDto(@NotNull final UserDto dto) {
        return new User(
                dto.getUserID(),
                dto.getAccessRoles(),
                dto.getUserName(),
                dto.getPasswordHash());
    }
}
