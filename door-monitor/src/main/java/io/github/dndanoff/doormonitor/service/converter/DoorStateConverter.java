package io.github.dndanoff.doormonitor.service.converter;

import org.springframework.stereotype.Service;

import io.github.dndanoff.doormonitor.dto.DoorStateTransitionDto;
import io.github.dndanoff.doormonitor.entity.DoorStateTransition;

/**
 * DoorStateConverter
 */
@Service
public class DoorStateConverter implements GenericObjectConverter<DoorStateTransition, DoorStateTransitionDto>{
    
    public DoorStateTransitionDto entityToDto(DoorStateTransition entity) {
        if (entity == null) {
            return null;
        }

        DoorStateTransitionDto dto = new DoorStateTransitionDto();
        dto.setId(entity.getId());
        dto.setActive(entity.getActive());
        dto.setDoorId(entity.getDoorId());
        dto.setCreateTime(entity.getCreateTime());
        dto.setValue(entity.getValue());

        return dto;
    }

    public DoorStateTransition dtoToEntity(DoorStateTransitionDto dto) {
        if (dto == null) {
            return null;
        }

        DoorStateTransition entity = new DoorStateTransition();
        entity.setId(dto.getId());
        entity.setActive(dto.getActive());
        entity.setDoorId(dto.getDoorId());
        entity.setCreateTime(dto.getCreateTime());
        entity.setValue(dto.getValue());

        return entity;
    }
    
}