package dev.ranggalabs.dbcache.component;

import dev.ranggalabs.dbcache.repository.CarTypeRepository;
import dev.ranggalabs.dbcache.repository.ItemTypeRepository;
import dev.ranggalabs.dbcache.repository.MeasurementRepository;
import dev.ranggalabs.dbcache.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements ApplicationRunner {

    @Autowired
    private ItemTypeRepository itemTypeRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        initItemType();
        initVehicleType();
        initCarType();
        initMeasurement();
    }

    private void initMeasurement() {
        measurementRepository.save(measurementRepository.findAllFromDb());
    }

    private void initCarType() {
        carTypeRepository.save(carTypeRepository.findAllFromDb());
    }

    private void initVehicleType() {
        vehicleTypeRepository.save(vehicleTypeRepository.findAllFromDb());
    }

    private void initItemType(){
        itemTypeRepository.save(itemTypeRepository.findAllFromDb());
    }
}
