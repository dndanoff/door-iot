package io.github.dndanoff.doormonitor.entrypoint.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dndanoff.doormonitor.dto.DoorDto;
import io.github.dndanoff.doormonitor.repository.DoorRepo;
import io.github.dndanoff.doormonitor.service.converter.DoorConverter;
import lombok.extern.slf4j.Slf4j;

/**
 * DoorReadingController
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/doors")
public class DoorController {

    private final DoorRepo doorRepo;
    private final DoorConverter doorConverter;

    @Autowired
    public DoorController(DoorRepo doorRepo, DoorConverter doorConverter) {
        this.doorRepo = doorRepo;
        this.doorConverter = doorConverter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<DoorDto> getAllDoors(){
        log.debug("Received call to getAllDoors");
        return doorConverter.entityToDto(doorRepo.findAll());
    }

}