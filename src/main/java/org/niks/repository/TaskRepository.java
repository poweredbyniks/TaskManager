package org.niks.repository;

import com.sun.istack.NotNull;
import org.niks.entity.Status;
import org.niks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT * FROM projects WHERE projectID = ?1")
    List<Task> findAllByProject(@NotNull final Long projectID);

    List<Task> findAllByTaskNameContainingAndTaskDescriptionContaining(@NotNull final String word);

    @Modifying
    @Query("UPDATE Task SET taskName = ?1, taskDescription = ?2, startDate = ?3, finishDate = ?4, " +
            "taskStatus = ?5 WHERE taskID = ?6")
    void update(@NotNull final String taskName, @NotNull final String taskDescription,
                @NotNull final Date startDate, @NotNull final Date finishDate, @NotNull final Status status,
                @NotNull final Long taskID);
}
