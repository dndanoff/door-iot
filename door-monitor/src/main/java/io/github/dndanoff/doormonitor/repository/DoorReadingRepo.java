package io.github.dndanoff.doormonitor.repository;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.doormonitor.entity.DoorReading;
import lombok.extern.slf4j.Slf4j;

/**
 * DoorReadingRepo
 */
@Slf4j
@Repository
public class DoorReadingRepo{
   
    private final DSLContext create;

    @Autowired
    public DoorReadingRepo(DSLContext create) {
        this.create = create;
    }

    public void saveAll(List<DoorReading> readings){

    }

    public void save(DoorReading reading){

    }

    public List<DoorReading> findAllUnprocessedReadings(){
        return null;
    }

    public void update(DoorReading reading){

    }
    public void updateAll(List<DoorReading> readings){

    }

}