package io.github.dndanoff.doormonitor.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DoorReading
 */
@Data
@NoArgsConstructor
public class DoorReading {
    private Long id;
    private Byte value;
    private Byte processed;
    private String doorName;
    private LocalDateTime createTime;
}