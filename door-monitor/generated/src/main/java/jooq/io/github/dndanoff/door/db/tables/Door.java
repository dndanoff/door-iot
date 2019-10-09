/*
 * This file is generated by jOOQ.
 */
package io.github.dndanoff.door.db.tables;


import io.github.dndanoff.door.db.DoorMonitor;
import io.github.dndanoff.door.db.Indexes;
import io.github.dndanoff.door.db.Keys;
import io.github.dndanoff.door.db.tables.records.DoorRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Door extends TableImpl<DoorRecord> {

    private static final long serialVersionUID = 1251144371;

    /**
     * The reference instance of <code>door_monitor.DOOR</code>
     */
    public static final Door DOOR = new Door();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DoorRecord> getRecordType() {
        return DoorRecord.class;
    }

    /**
     * The column <code>door_monitor.DOOR.ID</code>.
     */
    public final TableField<DoorRecord, String> ID = createField("ID", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>door_monitor.DOOR.NAME</code>.
     */
    public final TableField<DoorRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>door_monitor.DOOR</code> table reference
     */
    public Door() {
        this(DSL.name("DOOR"), null);
    }

    /**
     * Create an aliased <code>door_monitor.DOOR</code> table reference
     */
    public Door(String alias) {
        this(DSL.name(alias), DOOR);
    }

    /**
     * Create an aliased <code>door_monitor.DOOR</code> table reference
     */
    public Door(Name alias) {
        this(alias, DOOR);
    }

    private Door(Name alias, Table<DoorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Door(Name alias, Table<DoorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Door(Table<O> child, ForeignKey<O, DoorRecord> key) {
        super(child, key, DOOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DoorMonitor.DOOR_MONITOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_2, Indexes.CONSTRAINT_INDEX_20, Indexes.DOOR_NAME_INDX, Indexes.PRIMARY_KEY_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DoorRecord> getPrimaryKey() {
        return Keys.DOOR_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DoorRecord>> getKeys() {
        return Arrays.<UniqueKey<DoorRecord>>asList(Keys.DOOR_PK, Keys.CONSTRAINT_2, Keys.CONSTRAINT_20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door as(String alias) {
        return new Door(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door as(Name alias) {
        return new Door(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Door rename(String name) {
        return new Door(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Door rename(Name name) {
        return new Door(name, null);
    }
}
