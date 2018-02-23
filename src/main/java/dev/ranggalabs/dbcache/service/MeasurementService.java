package dev.ranggalabs.dbcache.service;

import dev.ranggalabs.dbcache.model.Measurement;

import java.util.List;

public interface MeasurementService {
    List<Measurement> findAllFromMem();
    List<Measurement> findAllFromDb();
}
