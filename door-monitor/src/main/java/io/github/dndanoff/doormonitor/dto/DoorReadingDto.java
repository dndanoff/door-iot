package io.github.dndanoff.doormonitor.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DoorReading
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoorReadingDto {
    private Long id;
    private Byte state;
    private LocalDateTime time;
    private String doorName;
}