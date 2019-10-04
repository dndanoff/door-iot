package io.github.dndanoff.doormonitor.service;

import org.springframework.stereotype.Service;

import io.github.dndanoff.doormonitor.dto.DoorReadingDto;
import io.github.dndanoff.doormonitor.entity.DoorReading;

/**
 * DoorReadingConverter
 */
@Service
public class DoorReadingConverter {

    public DoorReadingDto entityToDto(DoorReading entity){
        if(entity == null){
            return null;
        }

        DoorReadingDto dto = new DoorReadingDto();
        dto.setId(entity.getId());
        dto.setState(entity.getState());
        dto.setTime(entity.getTime());
        dto.setDoorName(entity.getDoorName());

        return dto;
    } 

    public DoorReading dtoToEntity(DoorReadingDto dto){
        if(dto == null){
            return null;
        }

        DoorReading entity = new DoorReading();
        entity.setId(dto.getId());
        entity.setState(dto.getState());
        entity.setTime(dto.getTime());
        entity.setDoorName(dto.getDoorName());

        return entity;
    }
}