package org.niks.entity;

import lombok.*;
import org.niks.enums.AccessRoles;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    Long userID;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    AccessRoles accessRoles;

    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String passwordHash;

}
