package io.github.dndanoff.doormonitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.doormonitor.entity.DoorReading;

/**
 * DoorReadingRepo
 */
@Repository
public interface DoorReadingRepo extends CrudRepository<DoorReading, Long>{
    public DoorReading findFirstByOrderByTimeDesc();
}