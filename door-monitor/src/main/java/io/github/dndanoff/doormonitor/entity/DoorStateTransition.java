package io.github.dndanoff.doormonitor.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DoorState
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class DoorStateTransition {
    private String id;
    private String doorId;
    private Byte active;
    private Byte value;
    private LocalDateTime createTime;
}