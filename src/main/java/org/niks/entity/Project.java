package org.niks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long userID;
    Long projectID;
    String projectName;
    String projectDescription;
    Date startDate;
    Date finishDate;
    Status projectStatus;
    Date creationDate;
    @OneToMany
    Set<Task> tasks;
}
