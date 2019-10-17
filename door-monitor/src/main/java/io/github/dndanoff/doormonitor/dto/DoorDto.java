package io.github.dndanoff.doormonitor.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Door
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class DoorDto {
    private String id;
    private String name;
    private LocalDateTime lastUpdated;
    private DoorStateTransitionDto state;
}