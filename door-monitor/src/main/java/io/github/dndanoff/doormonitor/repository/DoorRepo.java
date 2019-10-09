package io.github.dndanoff.doormonitor.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.door.db.Tables;
import io.github.dndanoff.door.db.tables.records.DoorRecord;
import io.github.dndanoff.door.db.tables.records.DoorStateTransitionRecord;
import io.github.dndanoff.doormonitor.entity.Door;
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
        log.debug("Calling findAll with params: empty");
        List<Door> doors = create.select(Stream.of(Tables.DOOR.fields(), Tables.DOOR_STATE_TRANSITION.fields()).flatMap(Arrays::stream).collect(Collectors.toList()))
        .from(Tables.DOOR)
        .leftOuterJoin(Tables.DOOR_STATE_TRANSITION).on(Tables.DOOR.ID.eq(Tables.DOOR_STATE_TRANSITION.DOOR_ID)).and(Tables.DOOR_STATE_TRANSITION.ACTIVE.eq((byte) 1))
        .fetch(this::convertRecordToDoor);
        log.debug("Query findAll fetched: {}", doors);
        return doors;
    }

    public Door findById(String id) {
        log.debug("Calling findById with params: id={}", id);
        Door door = create.select(Stream.of(Tables.DOOR.fields(), Tables.DOOR_STATE_TRANSITION.fields()).flatMap(Arrays::stream).collect(Collectors.toList()))
        .from(Tables.DOOR)
        .leftOuterJoin(Tables.DOOR_STATE_TRANSITION).on(Tables.DOOR.ID.eq(Tables.DOOR_STATE_TRANSITION.DOOR_ID)).and(Tables.DOOR_STATE_TRANSITION.ACTIVE.eq((byte) 1))
        .where(Tables.DOOR.ID.eq(id))
        .fetchOne(this::convertRecordToDoor);
        log.debug("Query findById fetched: {}", door);
        return door;
    }

    public Door findByName(String name) {
        log.debug("Calling findByName with params: name={}", name);
        Door door = create.select(Stream.of(Tables.DOOR.fields(), Tables.DOOR_STATE_TRANSITION.fields()).flatMap(Arrays::stream).collect(Collectors.toList()))
        .from(Tables.DOOR)
        .leftOuterJoin(Tables.DOOR_STATE_TRANSITION).on(Tables.DOOR.ID.eq(Tables.DOOR_STATE_TRANSITION.DOOR_ID)).and(Tables.DOOR_STATE_TRANSITION.ACTIVE.eq((byte) 1))
        .where(Tables.DOOR.NAME.eq(name))
        .fetchOne(this::convertRecordToDoor);
        log.debug("Query findAll findByName: {}", door);
        return door;
    }

    public int[] saveAll(List<Door> doors){
        log.debug("Calling saveAll with params: doors={}", doors);
        List<DoorRecord> records = doors.stream().map(d -> create.newRecord(Tables.DOOR, d)).collect(Collectors.toList());
        return create.batchInsert(records).execute();
    }

    public Door save(Door door){
        log.debug("Calling save with params: door={}", door);
        DoorRecord record = create.newRecord(Tables.DOOR, door);
        record.store();
        return convertRecordToDoor(record);
    }

    public void saveAllStateTransition(List<DoorStateTransition> stateTransitions){
        log.debug("Calling saveAllStateTransition with params: stateTransitions={}", stateTransitions);
        create.batchInsert(stateTransitions.stream().map(s -> create.newRecord(Tables.DOOR_STATE_TRANSITION, s)).collect(Collectors.toList())).execute();
    }

    public void updateStateTransition(DoorStateTransition stateTransition){
        log.debug("Calling updateStateTransition with params: stateTransition={}", stateTransition);
        create.newRecord(Tables.DOOR_STATE_TRANSITION, stateTransition).update();
    }


    private Door convertRecordToDoor(Record r){
        Door door = r.into(Tables.DOOR).into(Door.class);
        DoorStateTransition transition = r.into(Tables.DOOR_STATE_TRANSITION).into(DoorStateTransition.class);
        if(transition.getId() != null){
            door.setState(transition);
        }

        return door;
    }

}