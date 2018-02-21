package dev.ranggalabs.dbcache.mysql.repository;

import dev.ranggalabs.dbcache.model.BaseType;

import java.util.List;

public interface MySQLItemTypeRepository {
    List<BaseType> findAll();
}
