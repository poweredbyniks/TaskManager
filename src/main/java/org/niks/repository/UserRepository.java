package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User SET passwordHash = ?1 WHERE userID = ?2")
    void updatePassword(@NotNull final String passwordHash, @NotNull final Long userID);
}
