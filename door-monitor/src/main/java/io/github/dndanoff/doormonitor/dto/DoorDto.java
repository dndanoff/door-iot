package io.github.dndanoff.doormonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Door
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoorDto {
    private Long id;
    private String name;
    private DoorStateTransitionDto state;
}