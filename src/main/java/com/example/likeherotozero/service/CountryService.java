package com.example.likeherotozero.service;

import com.example.likeherotozero.entity.CountryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class CountryService {

    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");

    public List<CountryEntity> getAllCountries()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query q = entityManager.createQuery("select e from CountryEntity e");
        List<CountryEntity> countryEntityList = q.getResultList();
        return countryEntityList;
    }
}