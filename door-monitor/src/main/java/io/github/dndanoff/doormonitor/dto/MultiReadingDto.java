package io.github.dndanoff.doormonitor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MultiReadingDto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiReadingDto {
    private List<DoorReadingDto> doorReadings;
}