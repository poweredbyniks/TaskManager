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
    Long taskID;
    @ManyToOne
    User user;
    @ManyToOne
    Project project;
    String taskName;
    String taskDescription;
    Date startDate;
    Date finishDate;
    Status taskStatus;
    Date creationDate;
}
