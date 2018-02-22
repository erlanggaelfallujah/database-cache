package dev.ranggalabs.dbcache.controller;

import dev.ranggalabs.dbcache.model.Measurement;
import dev.ranggalabs.dbcache.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(value = "/hsqldb", method = RequestMethod.GET)
    public List<Measurement> findAllFromMem() {
        return measurementService.findAllFromMem();
    }

    @RequestMapping(value = "/mysql", method = RequestMethod.GET)
    public List<Measurement> findAllFromDb() {
        return measurementService.findAllFromDb();
    }

}
