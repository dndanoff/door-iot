package io.github.dndanoff.doormonitor.entrypoint.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dndanoff.doormonitor.dto.MultiReadingDto;
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
@RequestMapping("/v1/readings")
public class MultiReadingController {

    private final DoorReadingRepo doorReadingRepo;
    private final DoorReadingConverter doorReadingConverter;

    @Autowired
    public MultiReadingController(DoorReadingRepo doorReadingRepo, DoorReadingConverter doorReadingConverter) {
        this.doorReadingRepo = doorReadingRepo;
        this.doorReadingConverter = doorReadingConverter;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void storeReading(@RequestBody @Valid MultiReadingDto multiReadingDto) {
        log.debug("Calling storeReading with params: multiReadingDto={}", multiReadingDto);
        List<DoorReading> doorReadings = multiReadingDto.getDoorReadings()
            .stream()
            .map(d -> doorReadingConverter.dtoToEntity(d))
            .collect(Collectors.toList());
        doorReadingRepo.saveAll(doorReadings);
    }

}