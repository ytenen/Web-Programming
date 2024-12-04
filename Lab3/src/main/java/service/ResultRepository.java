package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Result;


import java.io.Serializable;
import java.util.List;

@SessionScoped
public class ResultRepository implements Serializable {

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    public List<Result> findAll() {
        return entityManager.createQuery("SELECT r FROM model.Result r", Result.class).getResultList();
    }

    @Transactional
    public void save(Result entity) {
        entityManager.persist(entity);
    }
}