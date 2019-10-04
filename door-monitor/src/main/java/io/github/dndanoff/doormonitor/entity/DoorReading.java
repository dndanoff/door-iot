package io.github.dndanoff.doormonitor.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DoorReading
 */
@Data
@Entity
@Table(name = "DOOR_READING")
@NoArgsConstructor
public class DoorReading {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "state")
    private Byte state;
    @Column(name = "time", insertable=false)
    private LocalDateTime time;
    @Column(name = "processed", insertable=false)
    private Byte processed;
    @Column(name = "door_name")
    private String doorName;
}