package io.github.dndanoff.doormonitor.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DoorState
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoorStateTransitionDto {
    private Long id;
    private Long doorId;
    private Byte active;
    private Byte value;
    private LocalDateTime createTime;
}