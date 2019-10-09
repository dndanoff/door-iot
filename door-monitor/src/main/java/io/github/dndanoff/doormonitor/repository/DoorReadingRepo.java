package io.github.dndanoff.doormonitor.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.door.db.Tables;
import io.github.dndanoff.door.db.tables.records.DoorReadingRecord;
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

    public int[] saveAll(List<DoorReading> readings){
        log.debug("Calling saveAll with params: readings={}", readings);
        List<DoorReadingRecord> records = readings.stream().map(r -> create.newRecord(Tables.DOOR_READING, r)).collect(Collectors.toList());
        return create.batchInsert(records).execute();
    }

    public DoorReading save(DoorReading reading){
        log.debug("Calling save with params: reading={}", reading);
        DoorReadingRecord record = create.newRecord(Tables.DOOR_READING, reading);
        record.store();
        return convertRecordToDoorReading(record);
    }

    public List<DoorReading> findAllUnprocessedReadingsOrderedByDateAsc(){
        log.debug("Calling findAllUnprocessedReadingsOrderedByDateAsc with params: empty");
        List<DoorReading> readings = create.select(Tables.DOOR_READING.fields())
        .from(Tables.DOOR_READING)
        .where(Tables.DOOR_READING.PROCESSED.eq((byte) 0))
        .orderBy(Tables.DOOR_READING.CREATE_TIME)
        .fetch(this::convertRecordToDoorReading);
        log.debug("Query findAllUnprocessedReadingsOrderedByDateAsc fetched: {}", readings);
        return readings;
    }

    public DoorReading update(DoorReading reading){
        log.debug("Calling update with params: reading={}", reading);
        DoorReadingRecord record = create.newRecord(Tables.DOOR_READING, reading);
        record.update();
        return convertRecordToDoorReading(record);
    }

    public int[] updateAll(List<DoorReading> readings){
        log.debug("Calling updateAll with params: readings={}", readings);
        List<DoorReadingRecord> records = readings.stream().map(r -> create.newRecord(Tables.DOOR_READING, r)).collect(Collectors.toList());
        return create.batchUpdate(records).execute();
    }

    private DoorReading convertRecordToDoorReading(Record r){
        return r.into(Tables.DOOR_READING).into(DoorReading.class);
    }

}