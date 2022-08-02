package com.binubalan.pluginarchi.plugin.api.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class BaseDao<OBJECT, ID extends Serializable> implements IBaseDao<OBJECT, ID> {
    @Autowired
    protected SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OBJECT getById(Class<OBJECT> objectClass, ID id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(objectClass, id);
    }

    @Override
    public OBJECT create(OBJECT object) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        return object;
    }

    @Override
    public OBJECT update(ID id, OBJECT object) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(object);
        transaction.commit();
        return object;
    }

    @Override
    public List<OBJECT> getAll(Class<OBJECT> objectClass) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OBJECT> criteriaQuery = criteriaBuilder.createQuery(objectClass);
        Root<OBJECT> root = criteriaQuery.from(objectClass);
        CriteriaQuery<OBJECT> all = criteriaQuery.select(root);

        TypedQuery<OBJECT> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void removeById(ID id, Class<OBJECT> objectClass) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        OBJECT objectExisting = session.find(objectClass, id);
        if (objectExisting != null) {
            Transaction transaction = session.beginTransaction();
            session.delete(objectExisting);
            transaction.commit();
        } else {
            throw new Exception("Not found");
        }
    }
}