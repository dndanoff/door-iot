package io.github.dndanoff.doormonitor.service.converter;

import org.springframework.stereotype.Service;

import io.github.dndanoff.doormonitor.dto.DoorReadingDto;
import io.github.dndanoff.doormonitor.entity.DoorReading;

/**
 * DoorReadingConverter
 */
@Service
public class DoorReadingConverter implements GenericObjectConverter<DoorReading, DoorReadingDto>{

    public DoorReadingDto entityToDto(DoorReading entity){
        if(entity == null){
            return null;
        }

        DoorReadingDto dto = new DoorReadingDto();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setCreateTime(entity.getCreateTime());
        dto.setDoorName(entity.getDoorName());

        return dto;
    } 

    public DoorReading dtoToEntity(DoorReadingDto dto){
        if(dto == null){
            return null;
        }

        DoorReading entity = new DoorReading();
        entity.setId(dto.getId());
        entity.setValue(dto.getValue());
        entity.setCreateTime(dto.getCreateTime());
        entity.setDoorName(dto.getDoorName());

        return entity;
    }
}