package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Result;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Named("resultDAO")
public class ResultDAO implements Serializable {
    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    public List<Result> findAll() {
        return entityManager.createQuery("SELECT res FROM Result res", Result.class).getResultList();
    }

    @Transactional
    public void save(Result entity) {
        entityManager.persist(entity);
    }

}
