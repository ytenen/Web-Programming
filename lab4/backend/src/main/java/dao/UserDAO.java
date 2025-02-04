package dao;

import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class UserDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    @Transactional
    public void save(UserEntity user) {
        em.persist(user);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
                .setParameter("name", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<UserEntity> findById(Long id) {
        UserEntity user = em.find(UserEntity.class, id);
        if (user != null){
            return Optional.of(user);
        }
        return Optional.empty();
    }
}