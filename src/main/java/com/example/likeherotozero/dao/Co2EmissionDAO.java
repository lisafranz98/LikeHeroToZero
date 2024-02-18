package com.example.likeherotozero.dao;

import com.example.likeherotozero.entity.ChangelogEntity;
import com.example.likeherotozero.entity.Co2EmissionsEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
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

    public void addCo2Emission(Co2EmissionsEntity emission)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Co2EmissionsEntity managedCo2EmissionEntity = entityManager.merge(emission);

        ChangelogEntity changelogEntity = new ChangelogEntity();
        changelogEntity.setEmissionsId(managedCo2EmissionEntity.getEmissionsId());
        changelogEntity.setUserId(managedCo2EmissionEntity.getUserId());
        changelogEntity.setChangeDate(new Timestamp(System.currentTimeMillis()));
        changelogEntity.setChangeType("ADDITION");

        entityManager.persist(changelogEntity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateCo2Emission(Co2EmissionsEntity emission) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Co2EmissionsEntity existingEmission = entityManager.find(Co2EmissionsEntity.class, emission.getEmissionsId());
            if (existingEmission != null) {
                existingEmission.setCountryCode(emission.getCountryCode());
                existingEmission.setDate(emission.getDate());
                existingEmission.setAmountValue(emission.getAmountValue());
                existingEmission.setUserId(emission.getUserId());

                entityManager.merge(existingEmission);

                ChangelogEntity changelogEntity = new ChangelogEntity();
                changelogEntity.setEmissionsId(existingEmission.getEmissionsId());
                changelogEntity.setUserId(existingEmission.getUserId());
                changelogEntity.setChangeDate(new Timestamp(System.currentTimeMillis()));
                changelogEntity.setChangeType("UPDATE");
                entityManager.persist(changelogEntity);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
