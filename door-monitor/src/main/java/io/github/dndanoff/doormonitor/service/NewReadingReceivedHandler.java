package io.github.dndanoff.doormonitor.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import io.github.dndanoff.doormonitor.entity.Door;
import io.github.dndanoff.doormonitor.entity.DoorReading;
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
        Map<String, List<DoorReading>> unprocessedReadings = readingRepo.findAllUnprocessedReadings().stream().collect(Collectors.groupingBy(DoorReading::getDoorName));
        for(Map.Entry<String, List<DoorReading>> entry : unprocessedReadings.entrySet()){
            Door door = doorRepo.findByName(entry.getKey());
            for(DoorReading reading : entry.getValue()){
                // check if state was changed and save it
                reading.setProcessed((byte)1);
            }
        }
        readingRepo.updateAll(unprocessedReadings.entrySet().stream().flatMap(e -> e.getValue().stream()).collect(Collectors.toList();
    }

}