package com.binubalan.pluginarchi.plugin.api.services;

import java.util.List;
import java.util.Optional;

public interface IBaseService<OBJECT, ID> {

    List<OBJECT> getAll(Class<OBJECT> objectClass);

    OBJECT create(OBJECT object);

    Optional<OBJECT> getById(ID id, Class<OBJECT> objectClass);

    void removeById(ID id, Class<OBJECT> objectClass) throws Exception;
}