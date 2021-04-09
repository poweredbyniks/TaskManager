package org.niks.repository;

import com.sun.istack.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByProjectNameContainingAndAndProjectDescriptionContaining(@NotNull final String word);

    @Modifying
    @Query("UPDATE Project SET projectName = ?1, projectDescription = ?2, startDate = ?3, finishDate = ?4, " +
            "projectStatus = ?5 WHERE projectID = ?6")
    void update(@NotNull final String projectName, @NotNull final String projectDescription,
                @NotNull final Date startDate, @NotNull final Date finishDate, @NotNull final Status status,
                @NotNull final Long projectID);

}
