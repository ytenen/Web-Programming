package dao;

import entity.PointEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class PointDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public void save(PointEntity result) {
        em.persist(result);
    }

    public List<PointEntity> findByUserId(Long userId) {
        return em.createNamedQuery("PointEntity.findByUserId", PointEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    public void deleteByUserId(Long userId) {
        em.createNamedQuery("PointEntity.deleteByUserId")
                .setParameter("userId", userId)
                .executeUpdate();
    }
}