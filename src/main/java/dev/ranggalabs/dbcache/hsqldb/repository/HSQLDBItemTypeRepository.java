package dev.ranggalabs.dbcache.hsqldb.repository;

import dev.ranggalabs.dbcache.model.BaseType;

import java.util.List;

public interface HSQLDBItemTypeRepository {
    void save(List<BaseType> baseTypes);
    List<BaseType> findAll();
}
