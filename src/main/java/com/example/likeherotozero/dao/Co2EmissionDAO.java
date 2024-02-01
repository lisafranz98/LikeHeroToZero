package com.example.likeherotozero.dao;

import com.example.likeherotozero.entity.Co2EmissionsEntity;
import jakarta.persistence.*;

import java.util.List;

public class Co2EmissionDAO {
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");

    public List<Co2EmissionsEntity> getAllCo2EmissionData()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query q = entityManager.createQuery("select e from Co2EmissionsEntity e where e.amountValue != 0");
        q.setMaxResults(10);
        List<Co2EmissionsEntity> co2EmissionsEntityList = q.getResultList();
        return co2EmissionsEntityList;
    }

    public List<Co2EmissionsEntity> getCo2EmissionByCountry(String selectedCountry)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query q = entityManager.createQuery("select e from Co2EmissionsEntity e where e.countryCode = :countryCode and e.amountValue <> 0.00 order by e.date DESC ");
        q.setParameter("countryCode", selectedCountry).setMaxResults(1);
        List<Co2EmissionsEntity> co2EmissionsEntityList = q.getResultList();
        return co2EmissionsEntityList;
    }
}
