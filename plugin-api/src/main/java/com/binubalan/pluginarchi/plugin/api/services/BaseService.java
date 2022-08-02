package com.binubalan.pluginarchi.plugin.api.services;

import com.binubalan.pluginarchi.plugin.api.daos.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BaseService<OBJECT, ID> implements IBaseService<OBJECT, ID> {
    @Autowired
    private IBaseDao<OBJECT, ID> dao;

    public BaseService(IBaseDao<OBJECT, ID> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<OBJECT> getAll(Class<OBJECT> objectClass) {
        return dao.getAll(objectClass);
    }

    @Override
    @Transactional
    public OBJECT create(OBJECT object) {
        return dao.create(object);
    }

    @Override
    @Transactional
    public Optional<OBJECT> getById(ID id, Class<OBJECT> objectClass) {
        return Optional.ofNullable(dao.getById(objectClass, id));
    }

    @Override
    @Transactional
    public void removeById(ID id, Class<OBJECT> objectClass) throws Exception {
        dao.removeById(id, objectClass);
    }
}