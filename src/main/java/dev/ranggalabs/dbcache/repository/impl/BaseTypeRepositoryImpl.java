package dev.ranggalabs.dbcache.repository.impl;

import dev.ranggalabs.dbcache.model.BaseType;
import dev.ranggalabs.dbcache.repository.BaseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class BaseTypeRepositoryImpl implements BaseTypeRepository {

    private String tableName;

    @Autowired
    private Sql2o hsqldbSQL2O;
    @Qualifier("mysql")
    @Autowired
    private Sql2o mysqlSQL2O;

    @Override
    public void save(List<BaseType> baseTypes) {
        if(baseTypes==null || baseTypes.isEmpty()){
            return;
        }
        StringBuilder sql = new StringBuilder("INSERT INTO "+tableName+" VALUES ")
                .append("(:id,:name)");
        try (Connection conn = hsqldbSQL2O.beginTransaction();) {
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

    private List<BaseType> findAll(Connection conn){
        StringBuilder sql = new StringBuilder("SELECT * FROM "+tableName);
        try (Query query = conn.createQuery(sql.toString())) {
            return query.executeAndFetch(BaseType.class);
        }
    }

    @Override
    public List<BaseType> findAllFromDb() {
        try (Connection conn = mysqlSQL2O.open()) {
            return findAll(conn);
        }
    }

    @Override
    public List<BaseType> findAllFromMem() {
        try (Connection conn = hsqldbSQL2O.open();) {
            return findAll(conn);
        }
    }
}
