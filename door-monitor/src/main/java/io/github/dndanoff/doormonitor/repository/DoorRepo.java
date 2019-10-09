package io.github.dndanoff.doormonitor.repository;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.doormonitor.entity.Door;
import io.github.dndanoff.doormonitor.entity.DoorReading;
import io.github.dndanoff.doormonitor.entity.DoorStateTransition;
import lombok.extern.slf4j.Slf4j;

/**
 * DoorReadingRepo
 */
@Slf4j
@Repository
public class DoorRepo {

    private final DSLContext create;

    @Autowired
    public DoorRepo(DSLContext create) {
        this.create = create;
    }

    public List<Door> findAll() {
        return null;
    }

    public Door findById(Long id) {
        return null;
    }

    public Door findByName(String name) {
        return null;
    }

    public void addNewStateTransition(DoorStateTransition stateTransition){

    }

}