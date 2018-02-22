package dev.ranggalabs.dbcache.repository;

import dev.ranggalabs.dbcache.model.BaseType;

import java.util.List;

public interface BaseTypeRepository {
    void save(List<BaseType> baseTypes);
    List<BaseType> findAllFromDb();
    List<BaseType> findAllFromMem();
}
