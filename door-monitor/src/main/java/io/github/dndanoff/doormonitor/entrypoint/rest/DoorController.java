package io.github.dndanoff.doormonitor.entrypoint.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import io.github.dndanoff.doormonitor.dto.MultiReadingDto;
import io.github.dndanoff.doormonitor.entity.DoorReading;
import io.github.dndanoff.doormonitor.repository.DoorReadingRepo;
import io.github.dndanoff.doormonitor.repository.DoorRepo;
import io.github.dndanoff.doormonitor.service.DoorReadingConverter;
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

}