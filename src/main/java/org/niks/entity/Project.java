package org.niks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Column(name = "user_id")
    Long userID;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_id")
    Long projectID;

    @Column(name = "project_name")
    String projectName;

    @Column(name = "project_description")
    String projectDescription;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "finish_date")
    Date finishDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    Status projectStatus;

    @Column(name = "creation_date")
    Date creationDate;

    @OneToMany
    Set<Task> tasks = new HashSet<>();

    public Project(Long userID, Long projectID) {
        this.userID = userID;
        this.projectID = projectID;
    }
}
