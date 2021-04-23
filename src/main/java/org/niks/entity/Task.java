package org.niks.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    Long taskID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @Column(name = "task_name")
    String taskName;

    @Column(name = "task_description")
    String taskDescription;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "finish_date")
    Date finishDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    Status taskStatus;

    @Column(name = "creation_date")
    Date creationDate;
}
