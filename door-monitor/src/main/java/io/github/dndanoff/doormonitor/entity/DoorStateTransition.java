package io.github.dndanoff.doormonitor.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * DoorState
 */
public class DoorStateTransition {
    private Long id;
    private Long doorId;
    private Byte active;
    private Byte value;
    private LocalDateTime createTime;
}