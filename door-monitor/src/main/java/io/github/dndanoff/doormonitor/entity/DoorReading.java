package io.github.dndanoff.doormonitor.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DoorReading
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class DoorReading {
    private String id;
    private Byte value;
    private Byte processed;
    private String doorName;
    private LocalDateTime createTime;
}