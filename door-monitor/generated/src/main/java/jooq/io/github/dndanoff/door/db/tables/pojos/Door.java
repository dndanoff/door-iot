/*
 * This file is generated by jOOQ.
 */
package io.github.dndanoff.door.db.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


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
public class Door implements Serializable {

    private static final long serialVersionUID = -686373152;

    private String        id;
    private String        name;
    private LocalDateTime lastUpdated;

    public Door() {}

    public Door(Door value) {
        this.id = value.id;
        this.name = value.name;
        this.lastUpdated = value.lastUpdated;
    }

    public Door(
        String        id,
        String        name,
        LocalDateTime lastUpdated
    ) {
        this.id = id;
        this.name = name;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Door (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(lastUpdated);

        sb.append(")");
        return sb.toString();
    }
}
