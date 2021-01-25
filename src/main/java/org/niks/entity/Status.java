package org.niks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    PLANNED("planned"),
    WORKING("working"),
    DONE("done");
    private final String status;

}
