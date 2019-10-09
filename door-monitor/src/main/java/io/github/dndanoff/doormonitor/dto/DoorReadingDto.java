package io.github.dndanoff.doormonitor.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DoorReading
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class DoorReadingDto {
    private String id;
    private Byte value;
    private LocalDateTime createTime;
    private String doorName;
}