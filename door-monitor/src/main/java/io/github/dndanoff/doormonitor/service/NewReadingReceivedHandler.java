package io.github.dndanoff.doormonitor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import io.github.dndanoff.doormonitor.entity.Door;
import io.github.dndanoff.doormonitor.entity.DoorReading;
import io.github.dndanoff.doormonitor.entity.DoorStateTransition;
import io.github.dndanoff.doormonitor.repository.DoorReadingRepo;
import io.github.dndanoff.doormonitor.repository.DoorRepo;
import io.github.dndanoff.doormonitor.service.event.NewReadingReceived;
import lombok.extern.slf4j.Slf4j;

/**
 * NewReadingReceivedHandler
 */
@Slf4j
@Component
public class NewReadingReceivedHandler {

    private final DoorReadingRepo readingRepo;
    private final DoorRepo doorRepo;

    @Autowired
    public NewReadingReceivedHandler(DoorReadingRepo readingRepo, DoorRepo doorRepo) {
        this.readingRepo = readingRepo;
        this.doorRepo = doorRepo;
    }

    @EventListener
    public void handle(NewReadingReceived event) throws InterruptedException {
        Map<String, List<DoorReading>> unprocessedReadings = readingRepo.findAllUnprocessedReadingsOrderedByDateAsc().stream().collect(Collectors.groupingBy(DoorReading::getDoorName));
        Set<DoorStateTransition> transitions = new HashSet<>();
        for(Map.Entry<String, List<DoorReading>> entry : unprocessedReadings.entrySet()){
            Door door = doorRepo.findByName(entry.getKey());
            if(door == null){
                door = new Door(UUID.randomUUID().toString(), entry.getKey(), null, null);
                doorRepo.save(door);
            }

            DoorStateTransition originalDoorState = door.getState();
            for(DoorReading reading : entry.getValue()){
                if(door.getState() == null || door.getState().getValue() != reading.getValue()){
                    DoorStateTransition transition = new DoorStateTransition();
                    transition.setId(UUID.randomUUID().toString());
                    transition.setActive((byte)1);
                    transition.setDoorId(door.getId());
                    transition.setValue(reading.getValue());
                    transition.setCreateTime(reading.getCreateTime());
                    if(door.getState() != null){
                        door.getState().setActive((byte)0);
                        transitions.add(door.getState());
                    }
                    door.setState(transition);
                    door.setLastUpdated(LocalDateTime.now());
                    transitions.add(transition);
                }else {
                    door.setLastUpdated(LocalDateTime.now());
                }
                reading.setProcessed((byte)1);
            }

            //Original state must be updated
            if(originalDoorState != null &&  originalDoorState.getId() != door.getState().getId()){
                doorRepo.updateStateTransition(originalDoorState);
                transitions.remove(originalDoorState);
            }

            doorRepo.update(door);
        }
        doorRepo.saveAllStateTransition(transitions.stream().collect(Collectors.toList()));
        readingRepo.updateAll(unprocessedReadings.entrySet().stream().flatMap(e -> e.getValue().stream()).collect(Collectors.toList()));
    }

}