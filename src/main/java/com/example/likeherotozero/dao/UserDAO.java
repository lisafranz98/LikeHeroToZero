package com.example.likeherotozero.dao;

import com.example.likeherotozero.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class UserDAO {

    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");

    public UserEntity findByUsername(String username)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("select u from UserEntity u where u.username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }
}