package io.github.dndanoff.doormonitor.entrypoint.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dndanoff.doormonitor.dto.DoorReadingDto;
import io.github.dndanoff.doormonitor.dto.MultiReadingDto;
import io.github.dndanoff.doormonitor.entity.DoorReading;
import io.github.dndanoff.doormonitor.repository.DoorReadingRepo;
import io.github.dndanoff.doormonitor.service.DoorReadingConverter;
import io.github.dndanoff.doormonitor.service.event.NewReadingReceived;
import lombok.extern.slf4j.Slf4j;

/**
 * DoorReadingController
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/door-readings")
public class DoorReadingController {

    private final ApplicationEventPublisher publisher;
    private final DoorReadingRepo doorReadingRepo;
    private final DoorReadingConverter doorReadingConverter;

    @Autowired
    public DoorReadingController(DoorReadingRepo doorReadingRepo, DoorReadingConverter doorReadingConverter, ApplicationEventPublisher publisher) {
        this.doorReadingRepo = doorReadingRepo;
        this.doorReadingConverter = doorReadingConverter;
        this.publisher = publisher;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void storeDoorReading(@RequestBody @Valid DoorReadingDto doorReadingDto) {
        log.debug("Calling storeDoorReading with params: doorReadingDto={}", doorReadingDto);
        doorReadingRepo.save(doorReadingConverter.dtoToEntity(doorReadingDto));
        publisher.publishEvent(new NewReadingReceived());
    }

    @PostMapping(value = "/multiple", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void storeMultiReading(@RequestBody @Valid MultiReadingDto multiReadingDto) {
        log.debug("Calling storeMultiReading with params: multiReadingDto={}", multiReadingDto);
        List<DoorReading> doorReadings = multiReadingDto.getDoorReadings()
            .stream()
            .map(d -> doorReadingConverter.dtoToEntity(d))
            .collect(Collectors.toList());
        doorReadingRepo.saveAll(doorReadings);
        publisher.publishEvent(new NewReadingReceived());
    }

}