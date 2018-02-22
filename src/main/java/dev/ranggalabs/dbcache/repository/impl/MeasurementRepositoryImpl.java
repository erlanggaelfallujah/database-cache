package dev.ranggalabs.dbcache.repository.impl;

import dev.ranggalabs.dbcache.model.BaseType;
import dev.ranggalabs.dbcache.model.Measurement;
import dev.ranggalabs.dbcache.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository {

    @Autowired
    private Sql2o hsqldbSQL2O;
    @Qualifier("mysql")
    @Autowired
    private Sql2o mysqlSQL2O;

    @Override
    public void save(List<Measurement> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            return;
        }

        StringBuilder sql = new StringBuilder();
        if (measurements.get(0).getId() != null) {
            sql.append("INSERT INTO measurement VALUES (")
                    .append(":id,");
        }else {
            sql.append("INSERT INTO measurement(entrance_date,driver,item_type_id,vehicle_type_id,car_type_id) VALUES (");
        }
        sql.append(":entranceDate,:driver,:itemTypeId,:vehicleTypeId,:carTypeId)");

        try (Connection conn = hsqldbSQL2O.beginTransaction();) {
            for (Measurement measurement : measurements) {
                try (Query query = conn.createQuery(sql.toString());) {
                    if (measurement.getId() != null) {
                        query.addParameter("id", measurement.getId());
                    }
                    query.addParameter("entranceDate", measurement.getEntranceDate())
                            .addParameter("driver", measurement.getDriver())
                            .addParameter("itemTypeId", measurement.getItemTypeId())
                            .addParameter("vehicleTypeId", measurement.getVehicleTypeId())
                            .addParameter("carTypeId", measurement.getCarTypeId())
                            .executeUpdate();
                }
            }
            conn.commit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Measurement> findAll(Connection conn) {
        StringBuilder sql = new StringBuilder("SELECT * FROM measurement");
        try (Query query = conn.createQuery(sql.toString())) {
            return query
                    .addColumnMapping("entrance_date", "entranceDate")
                    .addColumnMapping("item_type_id", "itemTypeId")
                    .addColumnMapping("vehicle_type_id", "vehicleTypeId")
                    .addColumnMapping("car_type_id", "carTypeId")
                    .executeAndFetch(Measurement.class);
        }
    }

    @Override
    public List<Measurement> findAllFromDb() {
        try (Connection conn = mysqlSQL2O.open()) {
            return findAll(conn);
        }
    }

    @Override
    public List<Measurement> findAllFromMem() {
        try (Connection conn = hsqldbSQL2O.open();) {
            return findAll(conn);
        }
    }
}
