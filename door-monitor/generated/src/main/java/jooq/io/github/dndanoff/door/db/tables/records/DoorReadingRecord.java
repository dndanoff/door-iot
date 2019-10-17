/*
 * This file is generated by jOOQ.
 */
package io.github.dndanoff.door.db.tables.records;


import io.github.dndanoff.door.db.tables.DoorReading;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DoorReadingRecord extends UpdatableRecordImpl<DoorReadingRecord> implements Record5<String, Byte, LocalDateTime, Byte, String> {

    private static final long serialVersionUID = 1308817839;

    /**
     * Setter for <code>door_monitor.DOOR_READING.ID</code>.
     */
    public void setId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>door_monitor.DOOR_READING.ID</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>door_monitor.DOOR_READING.VALUE</code>.
     */
    public void setValue(Byte value) {
        set(1, value);
    }

    /**
     * Getter for <code>door_monitor.DOOR_READING.VALUE</code>.
     */
    public Byte getValue() {
        return (Byte) get(1);
    }

    /**
     * Setter for <code>door_monitor.DOOR_READING.CREATE_TIME</code>.
     */
    public void setCreateTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>door_monitor.DOOR_READING.CREATE_TIME</code>.
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>door_monitor.DOOR_READING.PROCESSED</code>.
     */
    public void setProcessed(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>door_monitor.DOOR_READING.PROCESSED</code>.
     */
    public Byte getProcessed() {
        return (Byte) get(3);
    }

    /**
     * Setter for <code>door_monitor.DOOR_READING.DOOR_NAME</code>.
     */
    public void setDoorName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>door_monitor.DOOR_READING.DOOR_NAME</code>.
     */
    public String getDoorName() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<String, Byte, LocalDateTime, Byte, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<String, Byte, LocalDateTime, Byte, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return DoorReading.DOOR_READING.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field2() {
        return DoorReading.DOOR_READING.VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field3() {
        return DoorReading.DOOR_READING.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field4() {
        return DoorReading.DOOR_READING.PROCESSED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return DoorReading.DOOR_READING.DOOR_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte component2() {
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component3() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte component4() {
        return getProcessed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getDoorName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value2() {
        return getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value3() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value4() {
        return getProcessed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getDoorName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorReadingRecord value1(String value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorReadingRecord value2(Byte value) {
        setValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorReadingRecord value3(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorReadingRecord value4(Byte value) {
        setProcessed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorReadingRecord value5(String value) {
        setDoorName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorReadingRecord values(String value1, Byte value2, LocalDateTime value3, Byte value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DoorReadingRecord
     */
    public DoorReadingRecord() {
        super(DoorReading.DOOR_READING);
    }

    /**
     * Create a detached, initialised DoorReadingRecord
     */
    public DoorReadingRecord(String id, Byte value, LocalDateTime createTime, Byte processed, String doorName) {
        super(DoorReading.DOOR_READING);

        set(0, id);
        set(1, value);
        set(2, createTime);
        set(3, processed);
        set(4, doorName);
    }
}
