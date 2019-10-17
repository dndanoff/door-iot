package io.github.dndanoff.doormonitor.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Door
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Door {
    private String id;
    private String name;
    private LocalDateTime lastUpdated;
    private DoorStateTransition state;
}