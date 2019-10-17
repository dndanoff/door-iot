package io.github.dndanoff.doormonitor.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.doormonitor.dto.DoorDto;
import io.github.dndanoff.doormonitor.entity.Door;

/**
 * DoorConverter
 */
@Service
public class DoorConverter implements GenericObjectConverter<Door, DoorDto> {

    private final DoorStateConverter coorStateConverter;

    @Autowired
    public DoorConverter(DoorStateConverter coorStateConverter) {
        this.coorStateConverter = coorStateConverter;
    }

    public DoorDto entityToDto(Door entity) {
        if (entity == null) {
            return null;
        }

        DoorDto dto = new DoorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLastUpdated(entity.getLastUpdated());
        dto.setState(coorStateConverter.entityToDto(entity.getState()));

        return dto;
    }

    public Door dtoToEntity(DoorDto dto) {
        if (dto == null) {
            return null;
        }

        Door entity = new Door();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLastUpdated(dto.getLastUpdated());
        entity.setState(coorStateConverter.dtoToEntity(dto.getState()));

        return entity;
    }

}