package dev.ranggalabs.dbcache.mysql.repository.impl;

import dev.ranggalabs.dbcache.model.BaseType;
import dev.ranggalabs.dbcache.mysql.repository.MySQLItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class MySQLItemTypeRepositoryImpl implements MySQLItemTypeRepository {

    @Qualifier("mysql")
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<BaseType> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM item_type");
        try (Connection conn = sql2o.open(); Query query = conn.createQuery(sql.toString())) {
            return query.executeAndFetch(BaseType.class);
        }
    }
}
