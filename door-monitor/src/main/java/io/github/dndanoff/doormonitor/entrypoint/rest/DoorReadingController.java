package io.github.dndanoff.doormonitor.entrypoint.rest;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.github.dndanoff.doormonitor.entity.DoorReading;
import io.github.dndanoff.doormonitor.repository.DoorReadingRepo;
import io.github.dndanoff.doormonitor.service.DoorReadingConverter;
import lombok.extern.slf4j.Slf4j;

/**
 * DoorReadingController
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/door-readings")
public class DoorReadingController {

    private final DoorReadingRepo doorReadingRepo;
    private final DoorReadingConverter doorReadingConverter;

    @Autowired
    public DoorReadingController(DoorReadingRepo doorReadingRepo, DoorReadingConverter doorReadingConverter) {
        this.doorReadingRepo = doorReadingRepo;
        this.doorReadingConverter = doorReadingConverter;
    }

    @GetMapping(value = "/latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public DoorReadingDto getLatestDoorReadings() {
        DoorReading entity = doorReadingRepo.findFirstByOrderByTimeDesc();
        return doorReadingConverter.entityToDto(entity);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void storeDoorReading(@RequestBody @Valid DoorReadingDto doorReadingDto) {
        log.debug("Calling storeDoorReading with params: doorReadingDto={}", doorReadingDto);
        doorReadingRepo.save(doorReadingConverter.dtoToEntity(doorReadingDto));
    }

}