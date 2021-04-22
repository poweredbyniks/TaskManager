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
    Long userID;
    AccessRoles accessRoles;

    String userName;

    String passwordHash;

}
