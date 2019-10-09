package io.github.dndanoff.doormonitor.entity;

import lombok.Data;

/**
 * Door
 */
@Data
public class Door {
    private Long id;
    private String name;
    private DoorStateTransition state;
}