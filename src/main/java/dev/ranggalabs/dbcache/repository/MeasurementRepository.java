package dev.ranggalabs.dbcache.repository;

import dev.ranggalabs.dbcache.model.Measurement;

import java.util.List;

public interface MeasurementRepository {
    void save(List<Measurement> measurements);
    List<Measurement> findAllFromDb();
    List<Measurement> findAllFromMem();
}
