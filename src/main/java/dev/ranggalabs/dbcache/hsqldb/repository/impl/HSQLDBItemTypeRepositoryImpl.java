package dev.ranggalabs.dbcache.hsqldb.repository.impl;

import dev.ranggalabs.dbcache.hsqldb.repository.HSQLDBItemTypeRepository;
import dev.ranggalabs.dbcache.model.BaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class HSQLDBItemTypeRepositoryImpl implements HSQLDBItemTypeRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public void save(List<BaseType> baseTypes) {
        if(baseTypes==null || baseTypes.isEmpty()){
            return;
        }
        StringBuilder sql = new StringBuilder("INSERT INTO item_type VALUES ")
                .append("(:id,:name)");
        try (Connection conn = sql2o.beginTransaction();) {
            for (BaseType baseType : baseTypes) {
                try (Query query = conn.createQuery(sql.toString());) {
                    query.addParameter("id",baseType.getId())
                    .addParameter("name",baseType.getName()).executeUpdate();
                }
            }
            conn.commit(true);
        } catch (Exception e) {
        }
    }

    @Override
    public List<BaseType> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM item_type");
        try (Connection conn = sql2o.open(); Query query = conn.createQuery(sql.toString())) {
            return query.executeAndFetch(BaseType.class);
        }
    }
}
