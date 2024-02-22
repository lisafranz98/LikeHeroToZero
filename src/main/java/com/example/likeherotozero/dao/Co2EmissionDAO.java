package com.example.likeherotozero.dao;

import com.example.likeherotozero.entity.ChangelogEntity;
import com.example.likeherotozero.entity.Co2EmissionsEntity;
import com.example.likeherotozero.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import jakarta.faces.context.FacesContext;

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

                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
                UserEntity user = (UserEntity) session.getAttribute("user");
                if (existingEmission.getUserId() != null) {
                    changelogEntity.setUserId(existingEmission.getUserId());
                }
                else {
                    changelogEntity.setUserId(user.getUserId());
                }

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

    public void deleteEmission(Integer emissionId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

//            Query deleteChangelogs = entityManager.createQuery("DELETE FROM ChangelogEntity c WHERE c.emissionsId = :emissionId");
//            deleteChangelogs.setParameter("emissionId", emissionId).executeUpdate();

            Co2EmissionsEntity emissionToDelete = entityManager.find(Co2EmissionsEntity.class, emissionId);
            if (emissionToDelete != null) {
                entityManager.remove(emissionToDelete);

                ChangelogEntity changelogEntity = new ChangelogEntity();
                changelogEntity.setEmissionsId(emissionToDelete.getEmissionsId());
                changelogEntity.setUserId(emissionToDelete.getUserId());
                changelogEntity.setChangeDate(new Timestamp(System.currentTimeMillis()));
                changelogEntity.setChangeType("DELETION");
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