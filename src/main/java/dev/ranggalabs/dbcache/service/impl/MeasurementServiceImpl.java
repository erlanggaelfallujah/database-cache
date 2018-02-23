package dev.ranggalabs.dbcache.service.impl;

import dev.ranggalabs.dbcache.model.Measurement;
import dev.ranggalabs.dbcache.repository.MeasurementRepository;
import dev.ranggalabs.dbcache.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public List<Measurement> findAllFromMem() {
        return measurementRepository.findAllFromMem();
    }

    @Override
    public List<Measurement> findAllFromDb() {
        return measurementRepository.findAllFromDb();
    }
}
